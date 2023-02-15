package com.solusibejo.analytics_debugger.model

import java.util.*

class DebuggerEventItem(
    var id: String?,
    @JvmField var timestamp: Long?,
    var name: String?,
    eventProps: List<Map<String, String>>?,
    userProps: List<Map<String, String>>?
) {
    var messages: List<DebuggerMessage>? = null
    var eventProps: MutableList<DebuggerProp>? = null
    var userProps: MutableList<DebuggerProp>? = null

    init {
        this.eventProps = ArrayList()
        if (eventProps != null) {
            for (eventProp in eventProps) {
                val prop = createProp(eventProp)
                if (prop != null) {
                    (this.eventProps as ArrayList<DebuggerProp>).add(prop)
                }
            }
        }
        this.userProps = ArrayList()
        if (userProps != null) {
            for (userProp in userProps) {
                val prop = createProp(userProp)
                if (prop != null) {
                    (this.userProps as ArrayList<DebuggerProp>).add(prop)
                }
            }
        }
    }

    private fun createMessage(messageMap: Map<String, String>): DebuggerMessage? {
        val propertyId = messageMap["propertyId"]
        val message = messageMap["message"]
        if (propertyId == null || message == null) {
            return null
        }
        var allowedTypesList: List<String?>? = ArrayList()
        val allowedTypesString = messageMap["allowedTypes"]
        if (allowedTypesString != null) {
            allowedTypesList = Arrays.asList(
                *allowedTypesString.split(",".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray())
        }
        return DebuggerMessage(
            propertyId, message, allowedTypesList,
            messageMap["providedType"]
        )
    }

    private fun createProp(prop: Map<String, String>): DebuggerProp? {
        val id = prop["id"]
        val name = prop["name"]
        val value = prop["value"]
        return if (id == null || name == null || value == null) {
            null
        } else DebuggerProp(id, name, value)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as DebuggerEventItem
        if (id != that.id) return false
        if (timestamp != that.timestamp) return false
        if (if (name != null) name != that.name else that.name != null) return false
        if (if (messages != null) messages != that.messages else that.messages != null) return false
        if (if (eventProps != null) eventProps != that.eventProps else that.eventProps != null) return false
        return if (userProps != null) userProps == that.userProps else that.userProps == null
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + if (name != null) name.hashCode() else 0
        result = 31 * result + if (messages != null) messages.hashCode() else 0
        result = 31 * result + if (eventProps != null) eventProps.hashCode() else 0
        result = 31 * result + if (userProps != null) userProps.hashCode() else 0
        return result
    }
}