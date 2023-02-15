package com.solusibejo.analytics_debugger.debugger

import android.content.Intent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import com.solusibejo.analytics_debugger.event_list.DebuggerEventsListActivity
import com.solusibejo.analytics_debugger.view.DebuggerViewContainer

internal class DebuggerTouchHandler(
    private val windowManager: WindowManager, private val layoutParams: WindowManager.LayoutParams,
    debuggerViewContainer: DebuggerViewContainer
) : OnTouchListener {
    private var initialY = 0
    private var initialTouchY = 0f
    private var initialX = 0
    private var initialTouchX = 0f
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = debuggerViewContainer.onClickListener
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                initialY = layoutParams.y
                initialX = layoutParams.x
                initialTouchX = event.rawX
                initialTouchY = event.rawY
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                layoutParams.y = initialY + (event.rawY - initialTouchY).toInt()
                layoutParams.x = initialX + (event.rawX - initialTouchX).toInt()
                windowManager.updateViewLayout(v, layoutParams)
                return true
            }
            MotionEvent.ACTION_UP -> {
                val endX = event.rawX
                val endY = event.rawY
                if (isAClick(initialTouchX, endX, initialTouchY, endY)) {
                    v.performClick()
                    onClickListener.onClick(v)
                    val eventsListActivityIntent =
                        Intent(v.context, DebuggerEventsListActivity::class.java)
                    v.context.startActivity(eventsListActivityIntent)
                }
                return true
            }
        }
        return false
    }

    private fun isAClick(startX: Float, endX: Float, startY: Float, endY: Float): Boolean {
        val differenceX = Math.abs(startX - endX)
        val differenceY = Math.abs(startY - endY)
        return !(differenceX > 5 || differenceY > 5)
    }
}