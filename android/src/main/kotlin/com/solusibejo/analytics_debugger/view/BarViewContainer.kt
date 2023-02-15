package com.solusibejo.analytics_debugger.view

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.solusibejo.analytics_debugger.R
import com.solusibejo.analytics_debugger.Util.eventHaveErrors
import com.solusibejo.analytics_debugger.Util.timeString
import com.solusibejo.analytics_debugger.model.DebuggerEventItem

class BarViewContainer @SuppressLint("InflateParams") constructor(layoutInflater: LayoutInflater) :
    DebuggerViewContainer {
    override val view: View
    private val timestamp: TextView
    private val eventName: TextView
    private val successIcon: ImageView
    private val dragHandle: ImageView

    init {
        view = layoutInflater.inflate(R.layout.bar_view, null)
        timestamp = view.findViewById(R.id.timestamp)
        eventName = view.findViewById(R.id.event_name)
        successIcon = view.findViewById(R.id.success_icon)
        dragHandle = view.findViewById(R.id.drag_handle)
    }

    override val onClickListener: View.OnClickListener
        get() = View.OnClickListener { setError(false) }

    private fun setError(hasError: Boolean) {
        if (hasError) {
            successIcon.setImageResource(R.drawable.warning)
            dragHandle.setImageResource(R.drawable.drag_handle_white)
            view.setBackgroundResource(R.color.error)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timestamp.setTextColor(view.resources.getColor(R.color.foregroundLighter, null))
            } else {
                timestamp.setTextColor(view.resources.getColor(R.color.foregroundLighter))
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                eventName.setTextColor(view.resources.getColor(R.color.background, null))
            } else {
                eventName.setTextColor(view.resources.getColor(R.color.background))
            }
        } else {
            successIcon.setImageResource(R.drawable.tick)
            dragHandle.setImageResource(R.drawable.drag_handle_grey)
            view.setBackgroundResource(R.color.background)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timestamp.setTextColor(view.resources.getColor(R.color.foregroundLight, null))
            } else {
                timestamp.setTextColor(view.resources.getColor(R.color.foregroundLight))
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                eventName.setTextColor(view.resources.getColor(R.color.foreground, null))
            } else {
                eventName.setTextColor(view.resources.getColor(R.color.foreground))
            }
        }
    }

    override fun showEvent(event: DebuggerEventItem) {
        timestamp.text = timeString(event.timestamp)
        eventName.text = event.name
        if (eventHaveErrors(event)) {
            setError(true)
        }
    }
}