package com.pavelvorobyev.diploma.util

import android.annotation.SuppressLint
import android.text.format.DateFormat
import com.pavelvorobyev.diploma.util.extensions.Empty
import java.text.SimpleDateFormat

object DateUtils {
    @SuppressLint("SimpleDateFormat")
    private val iso8601DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    private const val visiDateFormat = "dd MMM HH:mm"

    fun formatVisitDate(date: String): String {
        return try {
            val dateParsed = iso8601DateFormat.parse(date)
            DateFormat.format(visiDateFormat, dateParsed).toString()
        } catch (e: Exception) {
            e.printStackTrace()
            String.Empty
        }
    }
}