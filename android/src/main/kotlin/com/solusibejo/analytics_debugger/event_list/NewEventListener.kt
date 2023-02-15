package com.solusibejo.analytics_debugger.event_list

import com.solusibejo.analytics_debugger.model.DebuggerEventItem

interface NewEventListener {
    fun onNewEvent(event: DebuggerEventItem?)
}