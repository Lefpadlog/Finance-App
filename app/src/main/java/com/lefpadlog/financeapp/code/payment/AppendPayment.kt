package com.lefpadlog.financeapp.code.payment

import androidx.compose.runtime.MutableState
import com.lefpadlog.financeapp.code.data.AppData.selectedPayment
import com.lefpadlog.financeapp.code.data.payment.Payment
import com.lefpadlog.financeapp.code.date.cleanDate

fun appendToSelectedPayment(
    title: String, titleValid: MutableState<Boolean>,
    description: String, descriptionValid: MutableState<Boolean>,
    date: String, dateValid: MutableState<Boolean>,
    type: String, typeValid: MutableState<Boolean>,
    from: String, fromValid: MutableState<Boolean>,
    to: String, toValid: MutableState<Boolean>,
    interval: String, intervalValid: MutableState<Boolean>,
    amount: String, amountValid: MutableState<Boolean>
): Boolean {
    titleValid.value = ValidatePayment.title(title)
    descriptionValid.value = ValidatePayment.description(description)
    dateValid.value = ValidatePayment.date(date)
    typeValid.value = ValidatePayment.type(type)
    fromValid.value = ValidatePayment.from(from) && ValidatePayment.fromTo(from, to)
    toValid.value = ValidatePayment.to(to) && ValidatePayment.fromTo(from, to)
    intervalValid.value = ValidatePayment.interval(interval)
    amountValid.value = ValidatePayment.amount(amount, from)

    if (!titleValid.value) return false
    if (!descriptionValid.value) return false
    if (!dateValid.value) return false
    if (!typeValid.value) return false
    if (!fromValid.value) return false
    if (!toValid.value) return false
    if (!intervalValid.value) return false
    if (!amountValid.value) return false

    selectedPayment = Payment(
        title = title,
        description = description,
        date = cleanDate(date),
        type = type,
        from = from,
        to = to,
        amount = amount.replace(",", ".").toFloat(),
        interval = interval
    ).copy()

    return true
}