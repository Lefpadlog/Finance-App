package com.lefpadlog.financeapp.code.date

fun isDateValid(originalDate: String): Boolean {
    return try {
        convertDate(cleanDate(originalDate))
        true
    } catch (e: Exception) {
        false
    }
}