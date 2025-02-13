package com.kirabium.relayance.domain.model

import java.util.Calendar
import java.util.Date

data class Customer(val id: Int, val name: String, val email: String, val createdAt: Date) {
    fun isNewCustomer(customCalendar : Calendar = Calendar.getInstance()): Boolean {
        val today = customCalendar
        val createdAtCalendar = Calendar.getInstance().apply {
            time = createdAt
        }
        today.add(Calendar.MONTH, -3)
        return !createdAtCalendar.before(today)
    }
}
