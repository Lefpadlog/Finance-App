package com.lefpadlog.financeapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lefpadlog.financeapp.code.data.AppDatabase.paymentMethods

@Composable
fun EntireMoneyField() {

    var amount = 0f
    for (paymentMethod in paymentMethods.getAll())
        amount += paymentMethod.amount

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Amount:  ", fontSize = 30.sp)
        Text(text = "%.2f€".format(amount).replace(",", "."), fontSize = 30.sp)
    }

    SeparatorLine()
}