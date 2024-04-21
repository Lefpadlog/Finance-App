package com.lefpadlog.financeapp.code.data.payment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lefpadlog.financeapp.code.data.AppData
import com.lefpadlog.financeapp.code.data.AppDatabase.mainActivity
import com.lefpadlog.financeapp.code.data.AppDatabase.paymentMethods
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethod
import com.lefpadlog.financeapp.code.date.convertDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel(application: Application) : AndroidViewModel(application) {

    val getAllPayments: LiveData<List<Payment>>
    private val getRepeatedPayments: LiveData<List<Payment>>
    private val repository: PaymentRepository

    init {
        val paymentDao = PaymentDatabase.getDatabase(application).paymentDao()
        repository = PaymentRepository(paymentDao)
        getAllPayments = repository.getAllPayments
        getRepeatedPayments = repository.getRepeatedPayments
    }

    fun getAllFiltered(sorted: Boolean = false): List<Payment> {
        val payments = getAll(sorted)
        return payments.filter {
            it.title.contains(AppData.searchFilter.value) ||
                    it.from.contains(AppData.searchFilter.value) ||
                    it.to.contains(AppData.searchFilter.value) ||
                    it.date.contains(AppData.searchFilter.value)
        }
    }

    fun getAllOfPaymentMethod(paymentMethod: PaymentMethod, sorted: Boolean = false): List<Payment> {
        val payments = getAll(sorted)
        return payments.filter { it.from == paymentMethod.title || it.to == paymentMethod.title }
    }


    fun getAll(sorted: Boolean = false): List<Payment> {
        val payments = mutableListOf<Payment>()
        getAllPayments.observe(mainActivity)
        { for (payment in it) payments.add(payment) }
        if (sorted) payments.sortByDescending { convertDate(it.date) }
        return payments
    }

    fun getRepeated(sorted: Boolean = false): List<Payment> {
        val payments = mutableListOf<Payment>()
        getRepeatedPayments.observe(mainActivity)
        { for (payment in it) payments.add(payment) }
        if (sorted) payments.sortBy {
            when (it.interval) {
                "Every year" -> 1
                "Every month" -> 2
                "Every week" -> 3
                "Every day" -> 4
                else -> 5
            }
        }
        return payments.filter { it.interval != "Once" }
    }

    fun add(payment: Payment) {
        viewModelScope.launch(Dispatchers.IO)
        { repository.addPayment(payment) }
        val fromPaymentMethod = paymentMethods.getAll().find { it.title == payment.from }
        val toPaymentMethod = paymentMethods.getAll().find { it.title == payment.to }

        if (payment.type == "Pay in" || payment.type == "Transaction")
            if (toPaymentMethod != null) {
                toPaymentMethod.amount =
                    "%.2f".format(toPaymentMethod.amount + payment.amount).replace(",", ".").toFloat()
                paymentMethods.update(toPaymentMethod)
            }
        if (payment.type == "Pay out" || payment.type == "Transaction")
            if (fromPaymentMethod != null) {
                fromPaymentMethod.amount =
                    "%.2f".format(fromPaymentMethod.amount - payment.amount).replace(",", ".").toFloat()
                paymentMethods.update(fromPaymentMethod)
            }
    }

    fun update(payment: Payment) {
        viewModelScope.launch(Dispatchers.IO)
        { repository.updatePayment(payment) }
    }

    fun remove(payment: Payment) {
        viewModelScope.launch(Dispatchers.IO)
        { repository.removePayment(payment) }

        val fromPaymentMethod = paymentMethods.getAll().find { it.title == payment.from }
        val toPaymentMethod = paymentMethods.getAll().find { it.title == payment.to }

        if (payment.type == "Pay in" || payment.type == "Transaction")
            if (toPaymentMethod != null) {
                toPaymentMethod.amount =
                    "%.2f".format(toPaymentMethod.amount - payment.amount).replace(",", ".").toFloat()
                paymentMethods.update(toPaymentMethod)
            }
        if (payment.type == "Pay out" || payment.type == "Transaction")
            if (fromPaymentMethod != null) {
                fromPaymentMethod.amount =
                    "%.2f".format(fromPaymentMethod.amount + payment.amount).replace(",", ".").toFloat()
                paymentMethods.update(fromPaymentMethod)
            }
    }

}