package com.lefpadlog.financeapp.ui.screen.payments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppDatabase.mainActivity
import com.lefpadlog.financeapp.code.data.AppDatabase.paymentMethods
import com.lefpadlog.financeapp.code.data.AppDatabase.payments
import com.lefpadlog.financeapp.code.payment.updateRepeatedPayments
import com.lefpadlog.financeapp.ui.composables.BottomBar
import com.lefpadlog.financeapp.ui.PaymentScreen
import com.lefpadlog.financeapp.ui.composables.LoadingField
import com.lefpadlog.financeapp.ui.composables.NewItemButton
import com.lefpadlog.financeapp.ui.composables.NoItemsFoundScreen
import com.lefpadlog.financeapp.ui.composables.SearchField

@Composable
fun PaymentScreen(navController: NavController) {
    var loadedPayments by rememberSaveable { mutableStateOf(false) }
    payments.getAllPayments.observe(mainActivity) { loadedPayments = true }

    var loadedPaymentMethods by rememberSaveable { mutableStateOf(false) }
    paymentMethods.getAllPaymentMethods.observe(mainActivity) { loadedPaymentMethods = true }

    var afterLoaded by rememberSaveable { mutableStateOf(false) }

    if (!loadedPayments || !loadedPaymentMethods) {
        LoadingField()
    } else {
        if (!afterLoaded)
            updateRepeatedPayments()
        if (payments.getAll().isEmpty())
            NoItemsFoundScreen("No payments found! Add some by clicking +")
        else if (payments.getAllFiltered().isEmpty())
            NoItemsFoundScreen("No payments found for this search!")
        Column {
            SearchField(navController)
            PaymentsList(navController)
        }
        BottomBar(navController)
        NewItemButton(navController, PaymentScreen.NewPaymentScreen)
        afterLoaded = true
    }

}


