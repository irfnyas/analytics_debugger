package com.solusibejo.analytics_debugger.view

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.solusibejo.analytics_debugger.R
import com.solusibejo.analytics_debugger.Util.eventHaveErrors
import com.solusibejo.analytics_debugger.model.DebuggerEventItem
import java.util.*

class BubbleViewContainer @SuppressLint("InflateParams") constructor(layoutInflater: LayoutInflater) :
    DebuggerViewContainer {
    override val view: View
    private val bubble: ImageView
    private val counter: TextView
    private var countedEvents = 0

    init {
        view = layoutInflater.inflate(R.layout.bubble_view, null)
        bubble = view.findViewById(R.id.bubble)
        counter = view.findViewById(R.id.counter)
    }

    override val onClickListener: View.OnClickListener
        get() = View.OnClickListener {
            setError(false)
            countedEvents = 0
            counter.text = String.format(Locale.US, "%d", countedEvents)
        }

    private fun setError(hasError: Boolean) {
        bubble.setImageResource(R.mipmap.bubble)
        counter.setBackgroundResource(R.drawable.green_oval)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            counter.setTextColor(view.resources.getColor(R.color.background, null))
        } else {
            counter.setTextColor(view.resources.getColor(R.color.background))
        }
    }

    override fun showEvent(event: DebuggerEventItem) {
        countedEvents += 1
        counter.text = String.format(Locale.US, "%d", countedEvents)
        if (eventHaveErrors(event)) {
            setError(true)
        }
    }
}