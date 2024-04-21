package com.lefpadlog.financeapp.ui.screen.paymentmethods

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppData.selectedPaymentMethod
import com.lefpadlog.financeapp.code.data.AppDatabase.paymentMethods
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethod
import com.lefpadlog.financeapp.ui.theme.h1
import com.lefpadlog.financeapp.ui.theme.h3
import com.lefpadlog.financeapp.ui.theme.h4

@Composable
fun PaymentMethodsList(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(state = scrollState)
            .padding(bottom = 65.dp)
    ) {
        for (paymentMethod in paymentMethods.getAll().sortedBy { it.title }) {
            PaymentMethodCard(navController, paymentMethod)
        }
    }
}

@Composable
fun PaymentMethodCard(navController: NavController, paymentMethod: PaymentMethod) {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .height(80.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(10.dp)
            .clickable {
                selectedPaymentMethod = paymentMethod
                navController.navigate(com.lefpadlog.financeapp.ui.PaymentMethodsScreen.SinglePaymentMethodScreen.route)
            }
    ) {
        Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
            Row {
                Text(
                    text = paymentMethod.title,
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontSize = h1.fontSize,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "%.2f€".format(paymentMethod.amount),
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontSize = h3.fontSize,
                    textAlign = TextAlign.End,
                    modifier = Modifier.width(125.dp)
                )
            }
            Row {
                Text(
                    text = paymentMethod.information,
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontSize = h4.fontSize
                )
            }

        }
    }
}
