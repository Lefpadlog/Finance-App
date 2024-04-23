package com.lefpadlog.financeapp.ui.screen.paymentmethods.newpaymentmethod

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.paymentmethod.ValidatePaymentMethod
import com.lefpadlog.financeapp.ui.screen.paymentmethods.AddButton
import com.lefpadlog.financeapp.ui.screen.paymentmethods.AmountField
import com.lefpadlog.financeapp.ui.screen.paymentmethods.InputField
import com.lefpadlog.financeapp.ui.screen.paymentmethods.TypeField
import com.lefpadlog.financeapp.ui.screen.paymentmethods.DescriptionField

@Composable
fun NewPaymentMethodForm(navController: NavController) {
    val check = rememberSaveable { mutableStateOf(false) }

    val type = rememberSaveable { mutableStateOf("Select") }
    val typeValid = rememberSaveable { mutableStateOf(true) }
    val title = remember { mutableStateOf("") }
    val titleValid = rememberSaveable { mutableStateOf(true) }
    val amount = remember { mutableStateOf("") }
    val amountValid = rememberSaveable { mutableStateOf(true) }
    val description = remember { mutableStateOf("") }
    val descriptionValid = rememberSaveable { mutableStateOf(true) }
    val information = remember { mutableStateOf("") }
    val informationValid = rememberSaveable { mutableStateOf(true) }

    SideEffect {
        if (check.value) {
            typeValid.value = ValidatePaymentMethod.type(type.value)
            titleValid.value = ValidatePaymentMethod.title(title.value)
            amountValid.value = ValidatePaymentMethod.amount(amount.value)
            descriptionValid.value = ValidatePaymentMethod.description(description.value)
            informationValid.value = ValidatePaymentMethod.information(information.value, type.value)
        }
    }

    Row {
        TypeField(type, typeValid)
        AmountField(amount, amountValid)
    }

    Row {
        InputField("Title", title, titleValid)
    }

    if (type.value == "Crypto") {
        Row {
            InputField("Crypto API Link", information, informationValid)
        }
    } else if (type.value == "Credit" || type.value == "Debit") {
        Row {
            InputField("Bank", information, informationValid)
        }
    }


    Row {
        DescriptionField(description, descriptionValid)
    }

    Row {
        AddButton(
            navController, check,
            type.value, typeValid,
            title.value, titleValid,
            amount.value, amountValid,
            description.value, descriptionValid,
            information.value, informationValid
        )
    }

}