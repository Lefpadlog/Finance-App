package com.lefpadlog.financeapp.ui.screen.payments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppData.selectedPayment
import com.lefpadlog.financeapp.code.data.AppDatabase.payments
import com.lefpadlog.financeapp.code.data.payment.Payment
import com.lefpadlog.financeapp.code.date.convertDate
import com.lefpadlog.financeapp.ui.PaymentScreen.SinglePaymentScreen
import com.lefpadlog.financeapp.ui.theme.blueColor
import com.lefpadlog.financeapp.ui.theme.greenColor
import com.lefpadlog.financeapp.ui.theme.redColor
import java.time.LocalDate

@Composable
fun PaymentsList(navController: NavController) {

    LazyColumn(
        modifier = Modifier
            .padding(bottom = 65.dp)
    ) {
        var currentDate = convertDate(LocalDate.MIN)
        items(payments.getAllFiltered(true)) {
            if (currentDate != it.date) {
                DateField(it)
                currentDate = it.date
            }
            PaymentField(navController, it)
        }
    }
}

@Composable
fun DateField(payment: Payment) {
    Row {
        Text(
            text = payment.date,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 5.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(top = 5.dp, start = 5.dp, end = 10.dp)
                .clip(RoundedCornerShape(5.dp))
        )
    }
}

@Composable
fun PaymentField(navController: NavController, payment: Payment) {
    Row(
        Modifier
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                selectedPayment = payment
                navController.navigate(SinglePaymentScreen.route)
            }
            .padding(bottom = 5.dp)
    ) {
        Text(
            text = payment.title,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .weight(1f)
                .padding(top = 3.dp, start = 5.dp)
        )
        Text(
            text = "%.2f€".format(payment.amount),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .width(90.dp)
                .padding(top = 3.dp, start = 5.dp),
            textAlign = TextAlign.End
        )
        Text(
            text = payment.type,
            modifier = Modifier
                .width(100.dp)
                .padding(top = 3.dp, start = 5.dp),
            color = when (payment.type) {
                "Pay out" -> redColor
                "Pay in" -> greenColor
                else -> blueColor
            }
        )

    }
}