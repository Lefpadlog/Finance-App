package com.lefpadlog.financeapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lefpadlog.financeapp.code.data.AppDatabase
import com.lefpadlog.financeapp.code.data.AppDatabase.settings
import com.lefpadlog.financeapp.code.date.convertDate
import com.lefpadlog.financeapp.ui.Screen
import com.lefpadlog.financeapp.ui.theme.h3
import java.time.LocalDate

@Composable
fun CheckPaymentBox(navController: NavController) {
    val shown = rememberSaveable {
        mutableStateOf(
            convertDate(settings.getDefault().lastChecked) < LocalDate.now().minusDays(14)
                    && AppDatabase.paymentMethods.getAll().isNotEmpty()
        )
    }

    if (shown.value)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.TopCenter)
                .padding(25.dp)
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .clickable {
                        settings.updateDefault(
                            settings
                                .getDefault()
                                .copy(lastChecked = convertDate(LocalDate.now()))
                        )
                        shown.value = false
                        navController.navigate(Screen.LastCheckedScreen.route)
                    }
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .border(2.dp, MaterialTheme.colorScheme.onTertiary, RoundedCornerShape(15.dp))
                    .padding(start = 15.dp, end = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Check your Payment Methods!", style = h3)
                CloseButton(shown)
            }
        }

}

@Composable
fun CloseButton(shown: MutableState<Boolean>) {
    Icon(
        modifier = Modifier
            .clickable {
                settings.updateDefault(
                    settings
                        .getDefault()
                        .copy(
                            lastChecked = convertDate(
                                LocalDate
                                    .now()
                                    .minusDays(11)
                            )
                        )
                )
                shown.value = false
            }
            .padding(10.dp),
        imageVector = Icons.Default.Close,
        contentDescription = ""
    )
}