package com.pavelvorobyev.diploma.util

import android.annotation.SuppressLint
import android.text.format.DateFormat
import com.pavelvorobyev.diploma.util.extensions.Empty
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @SuppressLint("SimpleDateFormat")
    private val iso8601DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    private const val visiDateFormat = "dd MMM HH:mm"

    private fun parseToIso8601(date: String): Date? {
        return iso8601DateFormat.parse(date)
    }

    fun formatVisitDate(date: String): String {
        return try {
            val dateParsed = parseToIso8601(date)
            DateFormat.format(visiDateFormat, dateParsed).toString()
        } catch (e: Exception) {
            e.printStackTrace()
            String.Empty
        }
    }
}