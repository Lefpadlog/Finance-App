package com.lefpadlog.financeapp.code.graph

import com.lefpadlog.financeapp.code.data.AppDatabase.payments
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethod
import com.lefpadlog.financeapp.code.date.convertDate
import java.time.LocalDate

class Graph(paymentMethod: PaymentMethod) {

    val entries = mutableMapOf<Float, Float>()
    var amountZeroPercentage = 0f
    var maxAmount: Float = 0f
    var minAmount: Float = 0f
    var oldestDate: LocalDate = LocalDate.now()
    var newestDate: LocalDate = LocalDate.now()

    init {
        loadValues(paymentMethod)
    }

    private fun loadValues(paymentMethod: PaymentMethod) {
        val paymentMethodPayments = payments.getAllOfPaymentMethod(paymentMethod, true)

        if (paymentMethodPayments.isEmpty())
            return

        maxAmount = paymentMethod.amount
        minAmount = paymentMethod.amount

        oldestDate = convertDate(paymentMethodPayments.last().date)
        newestDate = convertDate(paymentMethodPayments.first().date)
        val deltaTime = newestDate.toEpochDay() - oldestDate.toEpochDay() + 1

        var currentAmount = getPaymentMethodStartAmount(paymentMethod)
        entries[0f] = currentAmount
        for (payment in paymentMethodPayments.reversed()) {
            val percentageDate =
                (convertDate(payment.date).toEpochDay() - oldestDate.toEpochDay() + 1).toFloat() / deltaTime.toFloat()

            if (currentAmount > maxAmount)
                maxAmount = "%.2f".format(currentAmount).replace(",", ".").toFloat()
            if (currentAmount < minAmount)
                minAmount = "%.2f".format(currentAmount).replace(",", ".").toFloat()

            if (payment.to == paymentMethod.title) currentAmount += payment.amount
            if (payment.from == paymentMethod.title) currentAmount -= payment.amount

            entries[percentageDate] = currentAmount
        }
        entries[1.0f] = paymentMethod.amount

        val deltaAmount = maxAmount - minAmount
        for ((key, value) in entries)
            entries[key] = (value - minAmount) / deltaAmount

        amountZeroPercentage = -(minAmount / deltaAmount)
    }
}