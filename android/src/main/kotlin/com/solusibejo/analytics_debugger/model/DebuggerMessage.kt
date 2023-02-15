package com.solusibejo.analytics_debugger.model

class DebuggerMessage internal constructor(
    var propertyId: String, var message: String,
    var allowedTypes: List<String?>?,
    var providedType: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as DebuggerMessage
        if (propertyId != that.propertyId) return false
        if (message != that.message) return false
        if (if (allowedTypes != null) allowedTypes != that.allowedTypes else that.allowedTypes != null) return false
        return if (providedType != null) providedType == that.providedType else that.providedType == null
    }

    override fun hashCode(): Int {
        var result = propertyId.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + if (allowedTypes != null) allowedTypes.hashCode() else 0
        result = 31 * result + if (providedType != null) providedType.hashCode() else 0
        return result
    }
}