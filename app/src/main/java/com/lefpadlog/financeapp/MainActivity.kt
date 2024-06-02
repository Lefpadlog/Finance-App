package com.lefpadlog.financeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.lefpadlog.financeapp.code.data.AppData
import com.lefpadlog.financeapp.code.data.AppDatabase.payments
import com.lefpadlog.financeapp.code.data.AppDatabase.mainActivity
import com.lefpadlog.financeapp.code.data.AppDatabase.paymentMethods
import com.lefpadlog.financeapp.code.data.AppDatabase.settings
import com.lefpadlog.financeapp.code.data.payment.PaymentViewModel
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethodViewModel
import com.lefpadlog.financeapp.code.data.settings.SettingsViewModel
import com.lefpadlog.financeapp.ui.Navigation
import com.lefpadlog.financeapp.ui.theme.FinanceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mainActivity = this
                    payments = PaymentViewModel(application)
                    paymentMethods = PaymentMethodViewModel(application)
                    settings = SettingsViewModel(application)
                    AppData.searchFilter = rememberSaveable { mutableStateOf("") }

                    Navigation()
                }
            }
        }
    }
}