package com.phuhm.basemodule.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {
    fun getCurrentDate(): Date = Calendar.getInstance().time

    fun formatDate(date: Date, pattern: String, locale: Locale = Locale.getDefault()): String {
        return SimpleDateFormat(pattern, locale).format(date)
    }

    fun parseDate(dateString: String, pattern: String, locale: Locale = Locale.getDefault()): Date? {
        return try {
            SimpleDateFormat(pattern, locale).parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun getStartOfDay(date: Date): Date {
        return Calendar.getInstance().apply {
            time = date
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time
    }

    fun getEndOfDay(date: Date): Date {
        return Calendar.getInstance().apply {
            time = date
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }.time
    }

    fun isBetweenDate(date: Date, startDate: Date, endDate: Date): Boolean {
        val startOfDay = getStartOfDay(startDate)
        val endOfDay = getEndOfDay(endDate)
        return !date.before(startOfDay) && !date.after(endOfDay)
    }

    fun isSameDate(date1: Date, date2: Date): Boolean {
        val startOfDate1 = getStartOfDay(date1)
        val startOfDate2 = getStartOfDay(date2)
        return startOfDate1 == startOfDate2
    }
}