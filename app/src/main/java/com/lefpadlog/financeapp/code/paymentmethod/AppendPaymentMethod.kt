package com.lefpadlog.financeapp.code.paymentmethod

import androidx.compose.runtime.MutableState
import com.lefpadlog.financeapp.code.data.AppData.selectedPaymentMethod
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethod
import java.net.URL

fun appendToSelectedPaymentMethod (
    type: String, typeValid: MutableState<Boolean>, onEdit: Boolean,
    title: String, titleValid: MutableState<Boolean>,
    amount: String, amountValid: MutableState<Boolean>,
    description: String, descriptionValid: MutableState<Boolean>,
    information: String, informationValid: MutableState<Boolean>
): Boolean {
    typeValid.value = ValidatePaymentMethod.type(type)
    titleValid.value = if (onEdit) ValidatePaymentMethod.title(title) else ValidatePaymentMethod.addTitle(title)
    amountValid.value = ValidatePaymentMethod.amount(amount)
    descriptionValid.value = ValidatePaymentMethod.description(description)
    informationValid.value = ValidatePaymentMethod.information(information, type)

    if (!typeValid.value) return false
    if (!titleValid.value) return false
    if (!amountValid.value) return false
    if (!descriptionValid.value) return false
    if (!informationValid.value) return false

    selectedPaymentMethod = PaymentMethod(
        title = title,
        description = description,
        information = information,
        type = type,
        amount = amount.replace(",", ".").toFloat(),
    )
    return true
}

// TODO Crypto
fun getCurrentCryptoValue(cryptoName: String): Double? {

    val url = "https://api.coingecko.com/api/v3/simple/price?ids=$cryptoName&vs_currencies=eur"
    val response = URL(url).readText()

    return Regex("\\d*[.,]?\\d*").find(response)?.value?.toDoubleOrNull()

}