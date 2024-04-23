package com.lefpadlog.financeapp.ui.screen.paymentmethods.editpaymentmethod

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppData.selectedPaymentMethod
import com.lefpadlog.financeapp.code.paymentmethod.ValidatePaymentMethod

import com.lefpadlog.financeapp.ui.screen.paymentmethods.AmountField
import com.lefpadlog.financeapp.ui.screen.paymentmethods.DescriptionField
import com.lefpadlog.financeapp.ui.screen.paymentmethods.InputField
import com.lefpadlog.financeapp.ui.screen.paymentmethods.TypeField
import com.lefpadlog.financeapp.ui.screen.paymentmethods.DeleteButton
import com.lefpadlog.financeapp.ui.screen.paymentmethods.SaveButton

@Composable
fun EditPaymentMethodForm(navController: NavController) {
    val check = rememberSaveable { mutableStateOf(false) }

    val type = rememberSaveable { mutableStateOf(selectedPaymentMethod.type) }
    val typeValid = rememberSaveable { mutableStateOf(true) }
    val title = remember { mutableStateOf(selectedPaymentMethod.title) }
    val titleValid = rememberSaveable { mutableStateOf(true) }
    val amount = remember { mutableStateOf(selectedPaymentMethod.amount.toString()) }
    val amountValid = rememberSaveable { mutableStateOf(true) }
    val description = remember { mutableStateOf(selectedPaymentMethod.description) }
    val descriptionValid = rememberSaveable { mutableStateOf(true) }
    val information = remember { mutableStateOf(selectedPaymentMethod.information) }
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

    Row {
        InputField("Information", information, informationValid)
    }

    Row {
        DescriptionField(description, descriptionValid)
    }

    Row {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 3.dp)
        ) {
            SaveButton(
                navController, check,
                type.value, typeValid,
                title.value, titleValid,
                amount.value, amountValid,
                description.value, descriptionValid,
                information.value, informationValid
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 3.dp)
        ) {
            DeleteButton(navController)
        }


    }

}