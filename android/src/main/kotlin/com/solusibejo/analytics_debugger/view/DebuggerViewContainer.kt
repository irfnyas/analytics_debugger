package com.solusibejo.analytics_debugger.view

import android.view.View
import com.solusibejo.analytics_debugger.model.DebuggerEventItem

interface DebuggerViewContainer {
    fun showEvent(event: DebuggerEventItem)
    val view: View
    val onClickListener: View.OnClickListener
}