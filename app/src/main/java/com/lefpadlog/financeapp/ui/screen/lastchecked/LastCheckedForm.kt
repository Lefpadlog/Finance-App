package com.lefpadlog.financeapp.ui.screen.lastchecked

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppDatabase
import com.lefpadlog.financeapp.code.data.payment.Payment
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethod
import com.lefpadlog.financeapp.code.date.convertDate
import com.lefpadlog.financeapp.ui.Screen
import com.lefpadlog.financeapp.ui.theme.redColor
import java.time.LocalDate

@Composable
fun RowScope.DoneButton(
    navController: NavController,
    paymentMethodList: MutableList<PaymentMethod>,
    amountList: MutableList<String>
) {
    Button(
        onClick = {
            Log.d("TAG", "DoneButton: $amountList")
            correctingPaymentMethods(paymentMethodList, amountList)
            navController.navigate(Screen.PaymentScreen.route)
        },
        modifier = Modifier
            .weight(1f)
            .height(70.dp)
            .padding(top = 7.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            text = "Done",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

fun correctingPaymentMethods(
    paymentMethodList: MutableList<PaymentMethod>, amountList: MutableList<String>
) {
    for (i in 0 until amountList.size) {
        val newAmount = amountList[i].toFloatOrNull()
        val oldAmount = paymentMethodList[i].amount
        val paymentMethodName = paymentMethodList[i].title

        if (newAmount == null || newAmount == oldAmount)
            continue

        if (newAmount > oldAmount)
            AppDatabase.payments.add(
                Payment(
                    title = "Correcting $paymentMethodName",
                    description = "This payment is created to fix your Payment Method named $paymentMethodName",
                    date = convertDate(LocalDate.now()),
                    type = "Pay in",
                    from = "MissingNo",
                    to = paymentMethodName,
                    amount = newAmount - oldAmount,
                    interval = "Once"
                )
            )
        else
            AppDatabase.payments.add(
                Payment(
                    title = "Correcting $paymentMethodName",
                    description = "This payment is created to fix your Payment Method named $paymentMethodName",
                    date = convertDate(LocalDate.now()),
                    type = "Pay out",
                    from = paymentMethodName,
                    to = "MissingNo",
                    amount = oldAmount - newAmount,
                    interval = "Once"
                )
            )
    }
}

@Composable
fun RowScope.CancelButton(navController: NavController) {
    Button(
        onClick = {
            navController.navigate(Screen.PaymentScreen.route)
        },
        modifier = Modifier
            .weight(1f)
            .height(70.dp)
            .padding(top = 7.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = redColor)
    ) {
        Text(
            text = "Cancel",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
