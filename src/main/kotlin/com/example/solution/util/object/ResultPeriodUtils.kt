package com.example.solution.util.`object`

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object ResultPeriodUtils {
    private val startDate = LocalDate.parse("2025-04-01", DateTimeFormatter.ISO_DATE)
    private val endDate = LocalDate.parse("2025-04-15", DateTimeFormatter.ISO_DATE)

    fun isResultPeriod(): Boolean {
        val currentDate = LocalDate.now()
        return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate)
    }
}