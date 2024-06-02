package com.lefpadlog.financeapp.code.data.paymentmethod

import androidx.lifecycle.LiveData

class PaymentMethodRepository(private val paymentMethodDao: PaymentMethodDao) {

    val getAllPaymentMethods: LiveData<List<PaymentMethod>> =
        paymentMethodDao.getAllPaymentMethods()

    suspend fun addPaymentMethod(paymentMethod: PaymentMethod) {
        paymentMethodDao.addPaymentMethod(paymentMethod)
    }

    suspend fun updatePaymentMethod(paymentMethod: PaymentMethod) {
        paymentMethodDao.updatePaymentMethod(paymentMethod)
    }

    suspend fun removePaymentMethod(paymentMethod: PaymentMethod) {
        paymentMethodDao.removePaymentMethod(paymentMethod)
    }

}