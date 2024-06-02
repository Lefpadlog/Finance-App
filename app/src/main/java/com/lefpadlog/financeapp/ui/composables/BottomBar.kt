package com.lefpadlog.financeapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.DeveloperBoard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.ui.Screen

@Composable
fun BottomBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomCenter)
    ) {
        Row(
            modifier = Modifier
                .height(65.dp)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable { navController.navigate(Screen.PaymentScreen.route) }
                    .padding(18.dp),
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable { navController.navigate(Screen.RepeatedPaymentsScreen.route) }
                    .padding(18.dp),
                imageVector = Icons.Default.DateRange,
                contentDescription = "Repeated payments()",
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable { navController.navigate(Screen.PaymentMethodsScreen.route) }
                    .padding(18.dp),
                imageVector = Icons.Default.AccountBalance,
                contentDescription = "Payment types",
                tint = MaterialTheme.colorScheme.onPrimary
            )
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable { navController.navigate(Screen.GraphScreen.route) }
                    .padding(18.dp),
                imageVector = Icons.Default.AutoGraph,
                contentDescription = "User",
                tint = MaterialTheme.colorScheme.onPrimary
            )
//            Icon(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .weight(1f)
//                    .clickable { navController.navigate(Screen.DeveloperScreen.route) }
//                    .padding(18.dp),
//                imageVector = Icons.Default.DeveloperBoard,
//                contentDescription = "Developer",
//                tint = MaterialTheme.colorScheme.onPrimary
//            )
        }
    }

}