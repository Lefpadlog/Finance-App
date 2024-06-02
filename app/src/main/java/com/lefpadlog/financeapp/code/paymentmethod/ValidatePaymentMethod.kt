package com.lefpadlog.financeapp.code.paymentmethod

import com.lefpadlog.financeapp.code.data.AppDatabase.paymentMethods
import com.lefpadlog.financeapp.code.data.Constants

object ValidatePaymentMethod {
    fun type(type: String): Boolean {
        return type in Constants.PAYMENT_METHOD_VARIANTS
    }

    fun title(title: String): Boolean {
        return title.isNotBlank()
    }

    fun addTitle(title: String): Boolean {
        return title !in paymentMethods.getAll().map { it.title }
    }

    fun information(information: String, type: String): Boolean {
        if (type != "Cash")
            return information.isNotEmpty()
        return true
    }

    fun amount(amount: String): Boolean {
        if (amount.isBlank()) return false
        if (amount.replace(",", ".").toFloatOrNull() == null) return false
        return true
    }

    fun description(description: String): Boolean {
        return true
    }
}