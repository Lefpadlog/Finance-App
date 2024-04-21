package com.lefpadlog.financeapp.ui.screen.payments.singlepayment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppData.selectedPayment

import com.lefpadlog.financeapp.ui.PaymentScreen
import com.lefpadlog.financeapp.ui.Screen
import com.lefpadlog.financeapp.ui.composables.EditButton
import com.lefpadlog.financeapp.ui.composables.HomeButton
import com.lefpadlog.financeapp.ui.theme.blueColor
import com.lefpadlog.financeapp.ui.theme.greenColor
import com.lefpadlog.financeapp.ui.theme.h1
import com.lefpadlog.financeapp.ui.theme.h2
import com.lefpadlog.financeapp.ui.theme.h3
import com.lefpadlog.financeapp.ui.theme.lightGreyColor
import com.lefpadlog.financeapp.ui.theme.redColor
import com.lefpadlog.financeapp.ui.theme.yellowColor

@Composable
fun SinglePaymentScreen(navController: NavController) {

    HomeButton(navController = navController, Screen.PaymentScreen.route)
    EditButton(navController = navController, PaymentScreen.EditPaymentScreen.route)

    Column(
        modifier = Modifier.padding(top = 55.dp, start = 15.dp, end = 15.dp)
    ) {
        Row {
            Text(
                modifier = Modifier.weight(1f),
                text = selectedPayment.title,
                fontSize = h1.fontSize
            )
            Text(
                text = selectedPayment.date,
                textAlign = TextAlign.End,
                fontSize = h2.fontSize,
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        Text(
            text = selectedPayment.amount.toString() + "€",
            fontSize = h2.fontSize,
            color = yellowColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = selectedPayment.interval,
            fontSize = h2.fontSize
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = selectedPayment.type,
            fontSize = h2.fontSize,
            color = when (selectedPayment.type) {
                "Pay out" -> redColor
                "Pay in" -> greenColor
                else -> blueColor
            }
        )

        Row {
            Text(
                modifier = Modifier.weight(3f).align(Alignment.CenterVertically),
                text = selectedPayment.from,
                fontSize = h2.fontSize
            )

            Icon(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Arrow left",
                tint = when (selectedPayment.type) {
                    "Pay out" -> redColor
                    "Pay in" -> greenColor
                    else -> blueColor
                }
            )

            Text(
                modifier = Modifier.weight(3f).align(Alignment.CenterVertically),
                text = selectedPayment.to,
                fontSize = h2.fontSize
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier.weight(3f),
            text = selectedPayment.description,
            fontSize = h3.fontSize,
            color = lightGreyColor
        )

    }
}