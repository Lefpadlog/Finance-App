package com.lefpadlog.financeapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lefpadlog.financeapp.ui.screen.developer.DeveloperScreen
import com.lefpadlog.financeapp.ui.screen.payments.PaymentScreen
import com.lefpadlog.financeapp.ui.screen.payments.newpayment.NewPaymentScreen
import com.lefpadlog.financeapp.ui.screen.payments.editpayment.EditPaymentScreen
import com.lefpadlog.financeapp.ui.screen.paymentmethods.PaymentMethodsScreen
import com.lefpadlog.financeapp.ui.screen.paymentmethods.newpaymentmethod.NewPaymentMethodScreen
import com.lefpadlog.financeapp.ui.screen.paymentmethods.editpaymentmethod.EditPaymentMethodScreen
import com.lefpadlog.financeapp.ui.screen.paymentmethods.singlepaymentmethod.SinglePaymentMethodScreen
import com.lefpadlog.financeapp.ui.screen.payments.singlepayment.SinglePaymentScreen
import com.lefpadlog.financeapp.ui.screen.repeatedpayments.RepeatedPaymentsScreen
import com.lefpadlog.financeapp.ui.screen.graph.GraphScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.PaymentScreen.route) {
        composable(route = Screen.PaymentScreen.route)
        { PaymentScreen(navController = navController) }
            composable(route = PaymentScreen.NewPaymentScreen.route)
            { NewPaymentScreen(navController = navController) }
            composable(route = PaymentScreen.EditPaymentScreen.route)
            { EditPaymentScreen(navController = navController) }
            composable(route = PaymentScreen.SinglePaymentScreen.route)
            { SinglePaymentScreen(navController = navController) }

        composable(route = Screen.PaymentMethodsScreen.route)
        { PaymentMethodsScreen(navController = navController) }
            composable(route = PaymentMethodsScreen.NewPaymentMethodScreen.route)
            { NewPaymentMethodScreen(navController = navController) }
            composable(route = PaymentMethodsScreen.EditPaymentMethodScreen.route)
            { EditPaymentMethodScreen(navController = navController) }
            composable(route = PaymentMethodsScreen.SinglePaymentMethodScreen.route)
            { SinglePaymentMethodScreen(navController = navController) }

        composable(route = Screen.RepeatedPaymentsScreen.route)
        { RepeatedPaymentsScreen(navController = navController) }

        composable(route = Screen.GraphScreen.route)
        { GraphScreen(navController = navController) }

        composable(route = Screen.DeveloperScreen.route)
        { DeveloperScreen(navController = navController) }

    }

}