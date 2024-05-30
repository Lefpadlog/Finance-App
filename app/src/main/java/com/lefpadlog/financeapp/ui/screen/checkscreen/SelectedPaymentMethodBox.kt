package com.lefpadlog.financeapp.ui.screen.checkscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethod
import com.lefpadlog.financeapp.ui.theme.h1
import com.lefpadlog.financeapp.ui.theme.h3
import com.lefpadlog.financeapp.ui.theme.h5
import com.lefpadlog.financeapp.ui.theme.lightGreyColor

@Composable
fun RowScope.SelectedPaymentMethodBox(
    selectedPaymentMethod: PaymentMethod,
    amount: MutableState<String>
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .weight(1f)
            .fillMaxHeight()
            .border(2.dp, MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        DisplayInfo(selectedPaymentMethod)

        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(35.dp)
                .border(2.dp, MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(5.dp))
                .padding(5.dp),
            value = amount.value,
            onValueChange = { if (Regex("\\d*[.,]?\\d{0,2}").matches(it)) amount.value = it },
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = h3.fontSize,
                color = MaterialTheme.colorScheme.onPrimary
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onPrimary)
        )
    }
}

@Composable
fun DisplayInfo(selectedPaymentMethod: PaymentMethod) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(35.dp),
        textAlign = TextAlign.Center,
        text = selectedPaymentMethod.title,
        style = h1
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 35.dp),
        textAlign = TextAlign.Center,
        text = selectedPaymentMethod.description,
        style = h5
    )
}

