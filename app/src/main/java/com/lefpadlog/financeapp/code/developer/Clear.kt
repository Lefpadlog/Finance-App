package com.lefpadlog.financeapp.code.developer

import com.lefpadlog.financeapp.code.data.AppDatabase

object Clear {

    fun payments() {
        for (payment in AppDatabase.payments.getAll())
            AppDatabase.payments.remove(payment)
    }

    fun paymentMethods() {
        for (paymentMethods in AppDatabase.paymentMethods.getAll())
            AppDatabase.paymentMethods.remove(paymentMethods)
    }

    fun all() {
        payments()
        paymentMethods()
    }

}