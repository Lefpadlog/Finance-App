package com.lefpadlog.financeapp.code.data.paymentmethod

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lefpadlog.financeapp.code.data.AppDatabase
import com.lefpadlog.financeapp.code.data.AppDatabase.mainActivity
import com.lefpadlog.financeapp.code.data.AppDatabase.payments
import com.lefpadlog.financeapp.code.data.payment.Payment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentMethodViewModel(application: Application) : AndroidViewModel(application) {

    val getAllPaymentMethods: LiveData<List<PaymentMethod>>
    private val repository: PaymentMethodRepository

    init {
        val paymentMethodDao = PaymentMethodDatabase.getDatabase(application).paymentMethodDao()
        repository = PaymentMethodRepository(paymentMethodDao)
        getAllPaymentMethods = repository.getAllPaymentMethods
    }

    fun getAll() : List<PaymentMethod> {
        val paymentMethods = mutableListOf<PaymentMethod>()
        getAllPaymentMethods.observe(mainActivity) {
            for (paymentMethod in it)
                paymentMethods.add(paymentMethod)
        }
        return paymentMethods
    }
    
    fun add(paymentMethod: PaymentMethod) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPaymentMethod(paymentMethod)
        }
    }

    fun update(paymentMethod: PaymentMethod) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePaymentMethod(paymentMethod)
        }
    }

    fun remove(paymentMethod: PaymentMethod) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removePaymentMethod(paymentMethod)
        }
    }

}