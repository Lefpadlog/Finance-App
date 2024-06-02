package com.lefpadlog.financeapp.code.data

import com.lefpadlog.financeapp.MainActivity
import com.lefpadlog.financeapp.code.data.payment.PaymentViewModel
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethodViewModel
import com.lefpadlog.financeapp.code.data.settings.SettingsViewModel

object AppDatabase {
    lateinit var payments: PaymentViewModel
    lateinit var paymentMethods: PaymentMethodViewModel
    lateinit var settings: SettingsViewModel
    lateinit var mainActivity: MainActivity
}