package com.lefpadlog.financeapp.ui

sealed class Screen(val route: String) {
    data object PaymentScreen : Screen("paymentScreen")
    data object PaymentMethodsScreen : Screen("paymentMethods()Screen")
    data object RepeatedPaymentsScreen : Screen("repeatedPaymentsScreen")
    data object GraphScreen : Screen("graphScreen")
    data object LastCheckedScreen : Screen("lastCheckedScreen")
    data object DeveloperScreen : Screen("developerScreen")
}

sealed class PaymentScreen(val route: String) {
    data object NewPaymentScreen : Screen("newPaymentScreen")
    data object SinglePaymentScreen : Screen("singlePaymentScreen")
    data object EditPaymentScreen : Screen("editPaymentScreen")
}

sealed class PaymentMethodsScreen(val route: String) {
    data object NewPaymentMethodScreen : Screen("newPaymentMethodScreen")
    data object SinglePaymentMethodScreen : Screen("singlePaymentMethodScreen")
    data object EditPaymentMethodScreen : Screen("editPaymentMethodScreen")
}