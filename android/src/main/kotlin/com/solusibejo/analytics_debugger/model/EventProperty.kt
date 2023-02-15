package com.solusibejo.analytics_debugger.model

import java.util.*

class EventProperty(var id: String, var name: String, var value: String) {
    constructor(name: String, value: String) : this(UUID.randomUUID().toString(), name, value)
}