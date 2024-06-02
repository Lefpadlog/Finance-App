package com.lefpadlog.financeapp.ui.screen.lastchecked

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppDatabase
import com.lefpadlog.financeapp.ui.theme.h2

@Composable
fun LastCheckedScreen(navController: NavController) {

    val paymentMethodList = remember { AppDatabase.paymentMethods.getAll().toMutableList() }
    val selectedIndex = remember { mutableIntStateOf(0) }
    val selectedAmount = remember { mutableStateOf(paymentMethodList[0].amount.toString()) }
    val amountList = remember { AppDatabase.paymentMethods.getAll().map { it.amount.toString() }.toMutableList() }

    LaunchedEffect(selectedIndex.intValue) {
        selectedAmount.value = amountList[selectedIndex.intValue]
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .padding(25.dp)
                .padding(top = 25.dp),
            textAlign = TextAlign.Center,
            text = "Because you haven't checked if you payment methods amount in a while here you can update them",
            style = h2
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SwitchArrow(paymentMethodList, selectedIndex, false)
            SelectedPaymentMethodBox(paymentMethodList, selectedIndex, amountList, selectedAmount)
            SwitchArrow(paymentMethodList, selectedIndex, true)
        }

        Row(
            modifier = Modifier.padding(25.dp)
        ) {
            DoneButton(navController, paymentMethodList, amountList)
            Spacer(modifier = Modifier.width(16.dp))
            CancelButton(navController)
        }

    }


}
