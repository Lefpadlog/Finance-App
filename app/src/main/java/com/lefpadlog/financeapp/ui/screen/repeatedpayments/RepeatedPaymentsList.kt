package com.lefpadlog.financeapp.ui.screen.repeatedpayments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppDatabase.payments
import com.lefpadlog.financeapp.code.data.payment.Payment
import com.lefpadlog.financeapp.ui.screen.payments.PaymentField

@Composable
fun RepeatedPaymentsList(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(state = scrollState)
            .padding(bottom = 65.dp)
    ) {
        var currentInterval = ""
        for (payment in payments.getRepeated(true)) {
            if (payment.interval != currentInterval) {
                IntervalField(payment)
                currentInterval = payment.interval
            }
            PaymentField(navController, payment)
        }
    }

}

@Composable
fun IntervalField(payment: Payment) {
    Row {
        Text(
            text = payment.interval,
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

