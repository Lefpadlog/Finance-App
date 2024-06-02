package com.lefpadlog.financeapp.code.payment

import com.lefpadlog.financeapp.code.data.AppDatabase.payments
import com.lefpadlog.financeapp.code.data.payment.Payment
import com.lefpadlog.financeapp.code.date.convertDate
import java.time.LocalDate

fun updateRepeatedPayments() {
    for (payment in payments.getRepeated())
        updatePayment(payment)
}


fun updatePayment(payment: Payment) {
    while (convertDate(addInterval(payment.lastIntervalDate!!, payment.interval)) <= LocalDate.now()) {
        payment.lastIntervalDate = addInterval(payment.lastIntervalDate!!, payment.interval)
        payments.update(payment)

        payments.add(
            Payment(
                title = payment.title,
                description = payment.description,
                date = payment.lastIntervalDate!!,
                type = payment.type,
                from = payment.from,
                to = payment.to,
                interval = payment.interval,
                amount = payment.amount,
                originalId = payment.id
            )
        )
    }
}


fun addInterval(date: String, interval: String): String {
    return convertDate(
        when (interval) {
            "Every day" -> convertDate(date).plusDays(1)
            "Every week" -> convertDate(date).plusWeeks(1)
            "Every month" -> convertDate(date).plusMonths(1)
            "Every year" -> convertDate(date).plusYears(1)
            else -> convertDate(date).plusDays(1)
        }
    )
}