package com.lefpadlog.financeapp.ui.screen.payments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.EuroSymbol
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppData.selectedPayment
import com.lefpadlog.financeapp.code.data.AppDatabase.paymentMethods
import com.lefpadlog.financeapp.code.data.AppDatabase.payments
import com.lefpadlog.financeapp.code.data.Constants.PAYMENT_INTERVAL_VARIANTS
import com.lefpadlog.financeapp.code.data.Constants.PAYMENT_VARIANTS
import com.lefpadlog.financeapp.code.date.isDateValid
import com.lefpadlog.financeapp.code.payment.appendToSelectedPayment
import com.lefpadlog.financeapp.ui.Screen
import com.lefpadlog.financeapp.ui.composables.DropDownList
import com.lefpadlog.financeapp.ui.composables.FormTextField
import com.lefpadlog.financeapp.ui.theme.redColor

@Composable
fun RowScope.TypeField(type: MutableState<String>, valid: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .weight(1f)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 3.dp)
    ) {
        DropDownList(
            label = "Type",
            items = PAYMENT_VARIANTS,
            selectedItem = type,
            valid = valid.value
        )
    }
}

@Composable
fun RowScope.IntervalField(interval: MutableState<String>, valid: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .weight(1f)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 3.dp)
    ) {
        DropDownList(
            label = "Interval",
            items = PAYMENT_INTERVAL_VARIANTS,
            selectedItem = interval,
            valid = valid.value
        )
    }
}

@Composable
fun RowScope.FromField(
    type: MutableState<String>,
    from: MutableState<String>,
    valid: MutableState<Boolean>
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 3.dp)
    ) {
        if (type.value == "Transaction" || type.value == "Pay out") {
            if (from.value !in paymentMethods.getAll().map { it.title })
                from.value = "Select"
            DropDownList(
                label = "From",
                items = paymentMethods.getAll().map { it.title },
                selectedItem = from,
                valid = valid.value
            )
        } else {
            if (from.value == "Select" || from.value in paymentMethods.getAll().map { it.title })
                from.value = ""
            FormTextField(
                label = "From",
                value = from,
                valid = valid.value,
                enabled = type.value != "Select"
            )
        }
    }
}

@Composable
fun RowScope.ToField(
    type: MutableState<String>,
    to: MutableState<String>,
    valid: MutableState<Boolean>
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 3.dp)
    ) {
        if (type.value == "Transaction" || type.value == "Pay in") {
            if (to.value !in paymentMethods.getAll().map { it.title })
                to.value = "Select"
            DropDownList(
                label = "To",
                items = paymentMethods.getAll().map { it.title },
                selectedItem = to,
                valid = valid.value
            )
        } else {
            if (to.value == "Select" || to.value in paymentMethods.getAll().map { it.title })
                to.value = ""
            FormTextField(
                label = "To",
                value = to,
                valid = valid.value,
                enabled = type.value != "Select"
            )
        }
    }
}

@Composable
fun TitleField(title: MutableState<String>, valid: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 3.dp)
    ) {
        FormTextField(
            label = "Title",
            value = title,
            valid = valid.value
        )
    }
}

@Composable
fun RowScope.DescriptionField(description: MutableState<String>, valid: MutableState<Boolean>) {
    Box(
        modifier = Modifier
            .weight(5f)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 3.dp)
    ) {
        FormTextField(
            label = "Description",
            value = description,
            valid = valid.value,
            singleLine = false,
            height = 70 * 3,
            leaningIcon = Icons.Default.Description
        )

    }
}

@Composable
fun AmountField(amount: MutableState<String>, valid: MutableState<Boolean>) {
    FormTextField(label = "Amount",
        value = amount,
        valid = valid.value,
        leaningIcon = Icons.Default.EuroSymbol,
        keyboardType = KeyboardType.Decimal,
        onValueChange = {
            if (Regex("\\d*[.,]?\\d{0,2}").matches(it)) amount.value = it
        }
    )

}

@Composable
fun DateField(date: MutableState<String>, valid: MutableState<Boolean>) {
    FormTextField(label = "Date",
        value = date,
        valid = valid.value,
        leaningIcon = Icons.Default.DateRange,
        keyboardType = KeyboardType.Decimal,
        onValueChange = {
            if (Regex("\\d{0,2}[.-]\\d{0,2}[.-]\\d{0,4}").matches(it) && isDateValid(it)) date.value =
                it
        }
    )
}

@Composable
fun AddButton(
    navController: NavController, check: MutableState<Boolean>,
    title: String, titleValid: MutableState<Boolean>,
    description: String, descriptionValid: MutableState<Boolean>,
    date: String, dateValid: MutableState<Boolean>,
    type: String, typeValid: MutableState<Boolean>,
    from: String, fromValid: MutableState<Boolean>,
    to: String, toValid: MutableState<Boolean>,
    interval: String, intervalValid: MutableState<Boolean>,
    amount: String, amountValid: MutableState<Boolean>
) {
    Button(
        onClick = {
            check.value = true
            val valid = appendToSelectedPayment(
                title, titleValid,
                description, descriptionValid,
                date, dateValid,
                type, typeValid,
                from, fromValid,
                to, toValid,
                interval, intervalValid,
                amount, amountValid
            )
            if (valid) {
                payments.add(selectedPayment.copy())
                navController.navigate(Screen.PaymentScreen.route)
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
    title: String, titleValid: MutableState<Boolean>,
    description: String, descriptionValid: MutableState<Boolean>,
    date: String, dateValid: MutableState<Boolean>,
    type: String, typeValid: MutableState<Boolean>,
    from: String, fromValid: MutableState<Boolean>,
    to: String, toValid: MutableState<Boolean>,
    interval: String, intervalValid: MutableState<Boolean>,
    amount: String, amountValid: MutableState<Boolean>
) {
    Button(
        onClick = {
            check.value = true
            payments.remove(selectedPayment)
            val valid = appendToSelectedPayment(
                title, titleValid,
                description, descriptionValid,
                date, dateValid,
                type, typeValid,
                from, fromValid,
                to, toValid,
                interval, intervalValid,
                amount, amountValid
            )
            payments.add(selectedPayment)
            if (valid)
                navController.navigate(Screen.PaymentScreen.route)
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
fun RowScope.DeleteButton(navController: NavController) {
    Button(
        onClick = {
            if (selectedPayment.interval != "Once" && selectedPayment.originalId == null)
                selectedPayment.interval = "Once"
            else
                payments.remove(selectedPayment)
            navController.navigate(Screen.PaymentScreen.route)
        },
        modifier = Modifier
            .padding(top = 7.dp, start = 3.dp, end = 3.dp)
            .fillMaxWidth()
            .height(70.dp)
            .weight(1f),
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

@Composable
fun RowScope.DeleteAllButton(navController: NavController) {
    Button(
        onClick = {
            for (payment in payments.getAll()) {
                if (payment.originalId == selectedPayment.id)
                    payments.remove(payment)
            }
            payments.remove(selectedPayment)
            navController.navigate(Screen.PaymentScreen.route)
        },
        modifier = Modifier
            .padding(top = 7.dp, start = 3.dp, end = 3.dp)
            .fillMaxWidth()
            .height(70.dp)
            .weight(1f),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = redColor)
    ) {
        Text(
            text = "Delete All",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
