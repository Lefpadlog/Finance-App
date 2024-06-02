package com.lefpadlog.financeapp.ui.screen.repeatedpayments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppDatabase
import com.lefpadlog.financeapp.ui.PaymentScreen
import com.lefpadlog.financeapp.ui.composables.BottomBar
import com.lefpadlog.financeapp.ui.composables.NewItemButton
import com.lefpadlog.financeapp.ui.composables.NoItemsFoundScreen

@Composable
fun RepeatedPaymentsScreen(navController: NavController) {

    if (AppDatabase.payments.getRepeated().isEmpty())
        NoItemsFoundScreen("No repeated payments found! Add some by clicking +")
    Column(modifier = Modifier.padding(bottom = 65.dp)) {
        RepeatedPaymentsList(navController)
    }
    BottomBar(navController)
    NewItemButton(navController, PaymentScreen.NewPaymentScreen)

}