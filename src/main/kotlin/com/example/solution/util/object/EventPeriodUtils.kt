package com.example.solution.util.`object`

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object EventPeriodUtils {
    private val startDate = LocalDate.parse("2025-02-01", DateTimeFormatter.ISO_DATE)
    private val endDate = LocalDate.parse("2025-03-31", DateTimeFormatter.ISO_DATE)

    fun isEventPeriod(): Boolean {
        val currentDate = LocalDate.now()
        return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate)
    }
}