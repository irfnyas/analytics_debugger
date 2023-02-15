package com.solusibejo.analytics_debugger.model

class DebuggerProp internal constructor(var id: String?, var name: String?, var value: String?) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val prop = other as DebuggerProp
        if (id != prop.id) return false
        return if (name != prop.name) false else value == prop.value
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }
}