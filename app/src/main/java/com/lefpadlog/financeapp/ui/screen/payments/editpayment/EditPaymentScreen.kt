package com.lefpadlog.financeapp.ui.screen.payments.editpayment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.ui.Screen
import com.lefpadlog.financeapp.ui.composables.HomeButton

@Composable
fun EditPaymentScreen(navController: NavController) {

    HomeButton(navController = navController, Screen.PaymentScreen.route)

    Column(
        modifier = Modifier.padding(top = 55.dp)
    ) {
        EditPaymentForm(navController)
    }

}