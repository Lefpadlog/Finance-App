package com.lefpadlog.financeapp.ui.screen.paymentmethods

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EuroSymbol
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppData.selectedPaymentMethod
import com.lefpadlog.financeapp.code.data.AppDatabase.paymentMethods
import com.lefpadlog.financeapp.code.data.AppDatabase.payments
import com.lefpadlog.financeapp.code.data.Constants.PAYMENT_METHOD_VARIANTS
import com.lefpadlog.financeapp.code.paymentmethod.appendToSelectedPaymentMethod
import com.lefpadlog.financeapp.ui.Screen
import com.lefpadlog.financeapp.ui.composables.DropDownList
import com.lefpadlog.financeapp.ui.composables.FormTextField
import com.lefpadlog.financeapp.ui.theme.redColor

@Composable
fun RowScope.TypeField(type: MutableState<String>, valid: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .weight(3f)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 3.dp)
    ) {
        DropDownList(
            label = "Type",
            items = PAYMENT_METHOD_VARIANTS,
            selectedItem = type,
            valid = valid.value
        )
    }
}

@Composable
fun RowScope.AmountField(amount: MutableState<String>, valid: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .weight(2f)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 3.dp)
    ) {
        FormTextField(
            label = "Amount",
            value = amount,
            valid = valid.value,
            leaningIcon = Icons.Default.EuroSymbol,
            keyboardType = KeyboardType.Decimal,
            onValueChange = {
                if (Regex("\\d*[.,]?\\d{0,2}").matches(it)) amount.value = it
            }
        )
    }

}

@Composable
fun InputField(
    label: String,
    text: MutableState<String>,
    valid: MutableState<Boolean>,
    leaningIcon: ImageVector = Icons.Default.Edit
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 3.dp)
    ) {
        FormTextField(
            label = label,
            value = text,
            valid = valid.value,
            leaningIcon = leaningIcon
        )
    }
}

@Composable
fun DescriptionField(description: MutableState<String>, valid: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 3.dp)
    ) {
        FormTextField(
            label = "Description",
            value = description,
            valid = valid.value,
            singleLine = false,
            height = 70 * 3,
            leaningIcon = Icons.Default.Description,
        )

    }
}

@Composable
fun AddButton(
    navController: NavController, check: MutableState<Boolean>,
    type: String, typeValid: MutableState<Boolean>,
    title: String, titleValid: MutableState<Boolean>,
    amount: String, amountValid: MutableState<Boolean>,
    description: String, descriptionValid: MutableState<Boolean>,
    information: String, informationValid: MutableState<Boolean>
) {
    Button(
        onClick = {
            check.value = true
            val valid = appendToSelectedPaymentMethod(
                type, typeValid, false,
                title, titleValid,
                amount, amountValid,
                description, descriptionValid,
                information, informationValid
            )
            if (valid) {
                navController.navigate(Screen.PaymentMethodsScreen.route)
                paymentMethods.add(selectedPaymentMethod)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 7.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            text = "Add",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }

}

@Composable
fun SaveButton(
    navController: NavController, check: MutableState<Boolean>,
    type: String, typeValid: MutableState<Boolean>,
    title: String, titleValid: MutableState<Boolean>,
    amount: String, amountValid: MutableState<Boolean>,
    description: String, descriptionValid: MutableState<Boolean>,
    information: String, informationValid: MutableState<Boolean>
) {
    Button(
        onClick = {
            check.value = true
            val oldTitle = selectedPaymentMethod.title
            paymentMethods.remove(selectedPaymentMethod)
            val valid = appendToSelectedPaymentMethod(
                type, typeValid, true,
                title, titleValid,
                amount, amountValid,
                description, descriptionValid,
                information, informationValid
            )
            paymentMethods.add(selectedPaymentMethod)

            if (valid) {
                // Replace the old Payment Method in the transactions with the new one
                for (payment in payments.getAll()) {
                    if (payment.from == oldTitle) payment.from = selectedPaymentMethod.title
                    if (payment.to == oldTitle) payment.to = selectedPaymentMethod.title
                }

                navController.navigate(Screen.PaymentMethodsScreen.route)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 7.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            text = "Save",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }

}

@Composable
fun DeleteButton(navController: NavController) {
    Button(
        onClick = {
            paymentMethods.remove(selectedPaymentMethod)
            navController.navigate(Screen.PaymentMethodsScreen.route)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 7.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = redColor)
    ) {
        Text(
            text = "Delete",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
