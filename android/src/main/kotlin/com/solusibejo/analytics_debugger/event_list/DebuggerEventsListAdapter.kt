package com.solusibejo.analytics_debugger.event_list

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.solusibejo.analytics_debugger.debugger.DebuggerManager
import com.solusibejo.analytics_debugger.R
import com.solusibejo.analytics_debugger.Util.convertDpToPixel
import com.solusibejo.analytics_debugger.Util.eventHaveErrors
import com.solusibejo.analytics_debugger.Util.timeString
import com.solusibejo.analytics_debugger.event_list.DebuggerEventsListAdapter.DebuggerEventViewHolder
import com.solusibejo.analytics_debugger.model.DebuggerEventItem
import com.solusibejo.analytics_debugger.model.DebuggerMessage
import com.solusibejo.analytics_debugger.model.DebuggerProp

class DebuggerEventsListAdapter : RecyclerView.Adapter<DebuggerEventViewHolder>() {
    private val expendedEvents: MutableList<DebuggerEventItem> = ArrayList()

    init {
        if (DebuggerManager.events.size() > 0) {
            expendedEvents.add(DebuggerManager.events[0])
        }
        for (i in 0 until DebuggerManager.events.size()) {
            val event = DebuggerManager.events[i]
            if (eventHaveErrors(event)) {
                expendedEvents.add(event)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebuggerEventViewHolder {
        val rawLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.debugger_events_list_item, parent, false)
        return DebuggerEventViewHolder(rawLayout)
    }

    override fun onBindViewHolder(holder: DebuggerEventViewHolder, position: Int) {
        val event = DebuggerManager.events[position]
        val hasError = eventHaveErrors(event)
        val expended = expendedEvents.contains(event)
        holder.eventName.text = event.name
        holder.timestamp.text = timeString(event.timestamp)
        holder.expendedContent.removeAllViews()
        if (expended) {
            expendItem(holder, event, false)
        } else {
            collapseItem(holder, false)
        }
        if (hasError) {
            holder.successIcon.setImageResource(R.drawable.red_warning)
            holder.eventName.setTextColor(holder.itemView.resources.getColor(R.color.error))
        } else {
            holder.successIcon.setImageResource(R.drawable.tick)
            holder.eventName.setTextColor(holder.itemView.resources.getColor(R.color.foreground))
        }
        holder.itemView.setOnClickListener {
            if (expendedEvents.contains(event)) {
                collapseItem(holder, true)
                expendedEvents.remove(event)
            } else {
                expendItem(holder, event, true)
                expendedEvents.add(event)
            }
        }
    }

    private fun animateProps(
        holder: DebuggerEventViewHolder, startHeight: Int,
        endHeight: Int
    ) {
        val valueAnimator = ValueAnimator.ofInt(startHeight, endHeight)
        valueAnimator.addUpdateListener { animation ->
            holder.expendedContent.layoutParams.height = animation.animatedValue as Int
            holder.expendedContent.requestLayout()
        }
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = 500
        valueAnimator.start()
    }

    private fun collapseItem(holder: DebuggerEventViewHolder, animated: Boolean) {
        holder.expendedContent.removeAllViews()
        holder.expendButton.setImageResource(R.drawable.expend_arrow)
        val context = holder.itemView.context
        holder.expendedContent.setPadding(0, 0, 0, convertDpToPixel(0f, context).toInt())
        if (animated) {
            val startHeight = holder.expendedContent.height
            animateProps(holder, startHeight, 0)
        } else {
            holder.expendedContent.layoutParams.height = 0
            holder.expendedContent.requestLayout()
        }
    }

    private fun expendItem(
        holder: DebuggerEventViewHolder, event: DebuggerEventItem,
        animated: Boolean
    ) {
        holder.expendButton.setImageResource(R.drawable.collapse_arrow)
        val context = holder.itemView.context
        val layoutInflater = LayoutInflater.from(context)
        addPropRows(holder, event, context, layoutInflater, event.eventProps)
        addPropRows(holder, event, context, layoutInflater, event.userProps)
        if (holder.expendedContent.childCount > 0) {
            holder.expendedContent.removeViewAt(holder.expendedContent.childCount - 1)
        }
        holder.expendedContent.setPadding(0, 0, 0, convertDpToPixel(16f, context).toInt())
        if (animated) {
            val matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                (holder.expendedContent.parent as View).width,
                View.MeasureSpec.EXACTLY
            )
            val wrapContentMeasureSpec =
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            holder.expendedContent.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
            val targetHeight = holder.expendedContent.measuredHeight
            animateProps(holder, 0, targetHeight)
        } else {
            holder.expendedContent.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            holder.expendedContent.requestLayout()
        }
    }

    private fun addPropRows(
        holder: DebuggerEventViewHolder, event: DebuggerEventItem,
        context: Context, layoutInflater: LayoutInflater,
        props: List<DebuggerProp>?
    ) {
        if (props == null) {
            return
        }
        for (prop in props) {
            val propRowView = layoutInflater.inflate(
                R.layout.event_prop_row,
                holder.expendedContent, false
            )
            val propName = propRowView.findViewById<TextView>(R.id.prop_name)
            val propValue = propRowView.findViewById<TextView>(R.id.prop_value)
            propName.text = prop.name
            propValue.text = if (prop.value != null) prop.value else ""
            holder.expendedContent.addView(propRowView)
            layoutInflater.inflate(R.layout.prop_divider, holder.expendedContent, true)
        }
    }

    private fun boldifyErrorMessage(
        propertyName: String, message: String,
        allowedTypes: List<String>?,
        providedType: String?
    ): CharSequence {
        if (allowedTypes == null || allowedTypes.isEmpty() || providedType == null || providedType.isEmpty()) {
            return message
        }
        val boldIndexes: MutableList<Int> = ArrayList()
        val boldLengths: MutableList<Int> = ArrayList()
        boldIndexes.add(message.indexOf(propertyName))
        boldLengths.add(propertyName.length)
        for (allowedType in allowedTypes) {
            val index = message.indexOf(allowedType)
            boldIndexes.add(index)
            boldLengths.add(allowedType.length)
        }
        boldIndexes.add(message.indexOf(providedType))
        boldLengths.add(providedType.length)
        val formattedMessage = SpannableStringBuilder(message)
        for (i in boldIndexes.indices) {
            formattedMessage.setSpan(
                StyleSpan(Typeface.BOLD),
                boldIndexes[i], boldIndexes[i] + boldLengths[i],
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        return formattedMessage
    }

    override fun getItemCount(): Int {
        return DebuggerManager.events.size()
    }

    fun onNewItem(event: DebuggerEventItem) {
        expendedEvents.add(event)
        notifyDataSetChanged()
    }

    fun getExpendedEvents(): List<DebuggerEventItem> {
        return expendedEvents
    }

    class DebuggerEventViewHolder(itemView: View) : ViewHolder(itemView) {
        var eventName: TextView
        var expendedContent: LinearLayout
        var expendButton: ImageView
        var successIcon: ImageView
        var timestamp: TextView

        init {
            eventName = itemView.findViewById(R.id.event_name)
            expendedContent = itemView.findViewById(R.id.expended_content)
            expendButton = itemView.findViewById(R.id.expend_button)
            successIcon = itemView.findViewById(R.id.success_icon)
            timestamp = itemView.findViewById(R.id.timestamp)
        }
    }
}