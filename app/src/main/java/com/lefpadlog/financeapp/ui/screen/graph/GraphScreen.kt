package com.lefpadlog.financeapp.ui.screen.graph

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppDatabase.paymentMethods
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethod
import com.lefpadlog.financeapp.code.graph.Graph
import com.lefpadlog.financeapp.ui.composables.BottomBar
import com.lefpadlog.financeapp.ui.composables.DropDownList

@Composable
fun GraphScreen(navController: NavController) {

    val paymentMethod = rememberSaveable { mutableStateOf("Select") }
    val graph = Graph(paymentMethods.getAll().find { it.title == paymentMethod.value } ?: PaymentMethod("", ""))

    Column {
        DropDownList(
            label = "Payment method",
            items = paymentMethods.getAll().map { it.title },
            valid = true,
            selectedItem = paymentMethod,
            backgroundColor = MaterialTheme.colorScheme.background
        )

        GraphFrame(graph)
    }

    BottomBar(navController)
}