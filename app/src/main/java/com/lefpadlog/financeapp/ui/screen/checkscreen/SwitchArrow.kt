package com.lefpadlog.financeapp.ui.screen.checkscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lefpadlog.financeapp.code.data.paymentmethod.PaymentMethod
import com.lefpadlog.financeapp.ui.theme.lightGreyColor

@Composable
fun SwitchArrow(
    paymentMethodList: MutableList<PaymentMethod>,
    selectedIndex: MutableIntState,
    right: Boolean
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(50.dp)
            .fillMaxHeight()
            .clickable {
                if (right && selectedIndex.intValue != paymentMethodList.size - 1) selectedIndex.intValue += 1
                else if (!right && selectedIndex.intValue != 0) selectedIndex.intValue -= 1

            }
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = if (right) Icons.AutoMirrored.Filled.ArrowForwardIos
            else Icons.Default.ArrowBackIosNew,
            contentDescription = "",
            tint = if (right && selectedIndex.intValue == paymentMethodList.size - 1 || !right && selectedIndex.intValue == 0) lightGreyColor
            else MaterialTheme.colorScheme.onBackground
        )
    }
}
