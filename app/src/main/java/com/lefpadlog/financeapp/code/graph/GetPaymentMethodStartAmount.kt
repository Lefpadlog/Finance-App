package com.lefpadlog.financeapp.code.graph

import com.lefpadlog.financeapp.code.data.AppDatabase.payments
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethod

fun getPaymentMethodStartAmount(paymentMethod: PaymentMethod): Float {

    val paymentMethodPayments = payments.getAllOfPaymentMethod(paymentMethod, true)

    var amount = paymentMethod.amount

    for (payment in paymentMethodPayments) {
        if (payment.to == paymentMethod.title)
            amount -= payment.amount
        if (payment.from == paymentMethod.title)
            amount += payment.amount
    }

    return amount

}