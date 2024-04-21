package com.lefpadlog.financeapp.code.developer

import com.lefpadlog.financeapp.code.data.AppDatabase
import com.lefpadlog.financeapp.code.data.payment.Payment
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethod

object LoadTestingValues {

    fun paymentMethods() {
        AppDatabase.paymentMethods.add(
            PaymentMethod(
                "My Credit Card",
                "My credit card lorem ipsum",
                "Volksbank",
                "Credit",
                0f
            )
        )

        AppDatabase.paymentMethods.add(
            PaymentMethod(
                "My Debit Card",
                "My debit card lorem ipsum",
                "Volksbank",
                "Debit",
                0f
            )
        )

        AppDatabase.paymentMethods.add(
            PaymentMethod(
                "My Cash",
                "My cash Moneeeyyyy",
                "",
                "Cash",
                0f
            )
        )
    }

    fun default() {
        paymentMethods()
        AppDatabase.payments.add(
            Payment(
                title = "Minecraft",
                description = "Lorem ipsum",
                date = "10.03.2010",
                type = "Pay out",
                from = "My Debit Card",
                to = "Mojang",
                amount = 30.4f,
                interval = "Once",
            )
        )

        AppDatabase.payments.add(
            Payment(
                title = "Taschengeld",
                description = "Geld dass ich immer kriege",
                date = "01.01.2010",
                type = "Pay in",
                from = "Mama",
                to = "My Credit Card",
                amount = 50.0f,
                interval = "Every month",
            )
        )

        AppDatabase.payments.add(
            Payment(
                title = "1 password",
                description = "Passwortmanager zahlen",
                date = "16.03.2023",
                type = "Pay out",
                from = "My Debit Card",
                to = "1 password",
                amount = 80.0f,
                interval = "Every year",
            )
        )

        AppDatabase.payments.add(
            Payment(
                title = "TV",
                description = "Geld für Fernseher auftreiben",
                date = "10.10.2020",
                type = "Transaction",
                from = "My Credit Card",
                to = "My Debit Card",
                amount = 80.0f,
                interval = "Once",
            )
        )
    }

    fun extreme() {
        paymentMethods()
        AppDatabase.payments.add(
            Payment(
                title = "Testing only",
                description = "A long long time ago",
                date = "10.03.1904",
                type = "Pay out",
                from = "My Cash",
                to = "Gitschi",
                amount = .1f,
                interval = "Every year",
            )
        )
    }

    fun ultimate() {
        paymentMethods()
        AppDatabase.payments.add(
            Payment(
                title = "Testing extreme",
                description = "A long long long long time ago",
                date = "10.03.0001",
                type = "Pay out",
                from = "My Cash",
                to = "Jesus",
                amount = .1f,
                interval = "Every week",
            )
        )
    }

    fun graph() {
        paymentMethods()
        AppDatabase.payments.add(
            Payment(
                title = "Down",
                description = "Testing down",
                date = "10.03.2001",
                type = "Pay out",
                from = "My Cash",
                to = "Test",
                amount = 10f,
                interval = "Every week",
            )
        )
        AppDatabase.payments.add(
            Payment(
                title = "Up",
                description = "Testing up",
                date = "10.03.2001",
                type = "Pay in",
                from = "Test",
                to = "My Cash",
                amount = 50f,
                interval = "Every month",
            )

        )
    }

}