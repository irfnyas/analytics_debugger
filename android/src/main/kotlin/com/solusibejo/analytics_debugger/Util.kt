package com.solusibejo.analytics_debugger

import android.content.Context
import android.util.DisplayMetrics
import com.solusibejo.analytics_debugger.model.DebuggerEventItem
import java.text.SimpleDateFormat
import java.util.*

object Util {
    private fun eventsHaveErrors(items: List<DebuggerEventItem>?): Boolean {
        if (items == null) {
            return false
        }
        for (event in items) {
            if (event.messages != null) {
                return true
            }
        }
        return false
    }

    @JvmStatic
    fun eventHaveErrors(event: DebuggerEventItem?): Boolean {
        return eventsHaveErrors(object : ArrayList<DebuggerEventItem>() {
            init {
                event?.let { add(it) }
            }
        })
    }

    @JvmStatic
    fun timeString(timestamp: Long?): String {
        return if (timestamp == null) {
            ""
        } else SimpleDateFormat("HH:mm:ss.ms", Locale.US)
            .format(Date(timestamp))
    }

    @JvmStatic
    fun convertDpToPixel(dp: Float, context: Context?): Float {
        return if(context == null){
            0F
        } else {
            dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }
}