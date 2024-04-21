package com.lefpadlog.financeapp.code.validate

import com.lefpadlog.financeapp.code.data.AppDatabase
import com.lefpadlog.financeapp.code.data.Constants
import com.lefpadlog.financeapp.code.date.cleanDate
import com.lefpadlog.financeapp.code.date.isDateValid

object ValidatePayment {
    fun title(title: String): Boolean {
        return title.isNotBlank()
    }

    fun description(description: String): Boolean {
        return true
    }

    fun date(date: String): Boolean {
        if (date.isBlank()) return false
        if (!isDateValid(cleanDate(date))) return false
        return true
    }

    fun type(type: String): Boolean {
        return type in Constants.PAYMENT_VARIANTS
    }

    fun interval(interval: String): Boolean {
        return interval in Constants.PAYMENT_INTERVAL_VARIANTS
    }

    fun from(from: String): Boolean {
        return !(from.isBlank() || from == "Select")
    }

    fun to(to: String): Boolean {
        return !(to.isBlank() || to == "Select")
    }

    fun fromTo(from: String, to: String): Boolean {
        return from != to
    }

    fun amount(amount: String, from: String): Boolean {
        val fromType = AppDatabase.paymentMethods.getAll().find { it.title == from }?.type ?: ""
        val fromAmount =
            AppDatabase.paymentMethods.getAll().find { it.title == from }?.amount ?: 0f

        if (fromType == "Debit" && amount.toFloatOrNull() != null && fromAmount < amount.toFloat()) return false
        if (amount.isBlank()) return false
        if (amount.replace(",", ".").toFloatOrNull() == null) return false
        if (amount.replace(",", ".").toFloatOrNull() == 0f) return false
        return true
    }
}