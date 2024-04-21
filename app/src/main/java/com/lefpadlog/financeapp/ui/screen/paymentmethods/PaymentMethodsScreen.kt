package com.lefpadlog.financeapp.ui.screen.paymentmethods

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppDatabase
import com.lefpadlog.financeapp.ui.PaymentMethodsScreen
import com.lefpadlog.financeapp.ui.composables.BottomBar
import com.lefpadlog.financeapp.ui.composables.NewItemButton
import com.lefpadlog.financeapp.ui.composables.NoItemsFoundScreen

@Composable
fun PaymentMethodsScreen(navController: NavController) {

    if (AppDatabase.paymentMethods.getAll().isEmpty())
        NoItemsFoundScreen("No payment methods found! Add some by clicking +")
    Column(modifier = Modifier.padding(bottom = 65.dp)) {
        PaymentMethodsList(navController)
    }
    BottomBar(navController)
    NewItemButton(navController, PaymentMethodsScreen.NewPaymentMethodScreen)

}