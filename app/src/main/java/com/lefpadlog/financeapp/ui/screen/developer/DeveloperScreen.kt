package com.lefpadlog.financeapp.ui.screen.developer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.developer.Clear
import com.lefpadlog.financeapp.code.developer.LoadTestingValues
import com.lefpadlog.financeapp.ui.composables.BottomBar

@Composable
fun DeveloperScreen(navController: NavController) {

    Column {
        DeveloperButton(title = "Clear All") { Clear.all() }
        DeveloperButton(title = "Clear Payments") { Clear.payments() }
        DeveloperButton(title = "Clear Payment Methods") { Clear.paymentMethods() }
        Spacer(modifier = Modifier.height(20.dp))
        DeveloperButton(title = "Load Payment Methods") { LoadTestingValues.paymentMethods() }
        Spacer(modifier = Modifier.height(20.dp))
        DeveloperButton(title = "Load Defaults") { LoadTestingValues.default() }
        DeveloperButton(title = "Load Extreme") { LoadTestingValues.extreme() }
        DeveloperButton(title = "Load Ultimate") { LoadTestingValues.ultimate() }
        DeveloperButton(title = "Load Graph") { LoadTestingValues.graph() }
    }


    BottomBar(navController)

}