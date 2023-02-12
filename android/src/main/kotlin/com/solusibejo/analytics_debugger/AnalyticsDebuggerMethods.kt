package com.solusibejo.analytics_debugger

import android.app.Activity
import app.avo.androidanalyticsdebugger.DebuggerManager
import app.avo.androidanalyticsdebugger.DebuggerMode
import app.avo.androidanalyticsdebugger.EventProperty
import app.avo.androidanalyticsdebugger.PropertyError
import com.solusibejo.analytics_debugger.consts.Arguments
import io.flutter.plugin.common.MethodCall

object AnalyticsDebuggerMethods {
    fun show(
        activity: Activity,
        debugger: DebuggerManager,
        call: MethodCall,
    ){
        val mode = call.argument<Boolean>(Arguments.debuggerMode)!!
        val isSystemWide = call.argument<Boolean?>(Arguments.systemWide) ?: false
        var type = DebuggerMode.bubble

        if(!mode) type = DebuggerMode.bar

        if(isSystemWide){
            debugger.showDebugger(activity, type, true)
        }
        else {
            debugger.showDebugger(activity, type)
        }
    }

    fun hide(
        activity: Activity,
        debugger: DebuggerManager,
    ) {
        debugger.hideDebugger(activity)
    }

    fun send(
        debugger: DebuggerManager,
        call: MethodCall,
    ){
        val id = call.argument<Int>(Arguments.id)!!
        val name = call.argument<String>(Arguments.name)!!
        val values = call.argument<Map<String, Any>?>(Arguments.values)!!

        val events: ArrayList<EventProperty> = ArrayList()
        for ((key, value) in values){
            events.add(EventProperty(key, value.toString()))
        }

        debugger.publishEvent(
            id.toLong(),
            name,
            events,
            emptyList(),
        )
    }
}