package com.lefpadlog.financeapp.code.data

import androidx.compose.runtime.MutableState
import com.lefpadlog.financeapp.code.data.payment.Payment
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethod

object AppData {
    var selectedPayment = Payment(title = "Error", description = "You shouldn't see this")
    var selectedPaymentMethod = PaymentMethod(title = "Error", description = "You shouldn't see this")
    lateinit var searchFilter: MutableState<String>
}