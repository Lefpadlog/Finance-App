package com.lefpadlog.financeapp.ui.screen.paymentmethods.singlepaymentmethod

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppData.selectedPaymentMethod

import com.lefpadlog.financeapp.ui.PaymentMethodsScreen
import com.lefpadlog.financeapp.ui.Screen
import com.lefpadlog.financeapp.ui.composables.EditButton
import com.lefpadlog.financeapp.ui.composables.HomeButton
import com.lefpadlog.financeapp.ui.theme.h1
import com.lefpadlog.financeapp.ui.theme.h2
import com.lefpadlog.financeapp.ui.theme.h3
import com.lefpadlog.financeapp.ui.theme.lightGreyColor

@Composable
fun SinglePaymentMethodScreen(navController: NavController) {

    HomeButton(navController = navController, Screen.PaymentMethodsScreen.route)
    EditButton(navController = navController, PaymentMethodsScreen.EditPaymentMethodScreen.route)

    Column(
        modifier = Modifier.padding(top = 55.dp, start = 15.dp, end = 15.dp)
    ) {
        Row {
            Text(
                modifier = Modifier.weight(1f),
                text = selectedPaymentMethod.title,
                fontSize = h1.fontSize
            )
            Text(
                text = selectedPaymentMethod.amount.toString(),
                textAlign = TextAlign.End,
                fontSize = h2.fontSize,
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = selectedPaymentMethod.information,
            fontSize = h2.fontSize
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = selectedPaymentMethod.description,
            fontSize = h3.fontSize,
            color = lightGreyColor
        )

    }

}