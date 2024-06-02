package com.lefpadlog.financeapp.ui.screen.paymentmethods.editpaymentmethod

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.ui.Screen
import com.lefpadlog.financeapp.ui.composables.HomeButton

@Composable
fun EditPaymentMethodScreen(navController: NavController) {

    HomeButton(navController = navController, Screen.PaymentMethodsScreen.route)

    Column(
        modifier = Modifier.padding(top = 55.dp)
    ) {
        EditPaymentMethodForm(navController)
    }

}