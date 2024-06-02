package com.lefpadlog.financeapp.code.data.payment

import androidx.lifecycle.LiveData

class PaymentRepository(private val paymentDao: PaymentDao) {

    val getAllPayments: LiveData<List<Payment>> = paymentDao.getAllPayments()
    val getRepeatedPayments: LiveData<List<Payment>> = paymentDao.getRepeatedPayments()

    suspend fun addPayment(payment: Payment) {
        paymentDao.addPayment(payment)
    }

    suspend fun updatePayment(payment: Payment) {
        paymentDao.updatePayment(payment)
    }

    suspend fun removePayment(payment: Payment) {
        paymentDao.removePayment(payment)
    }


}