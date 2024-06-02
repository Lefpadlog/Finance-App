package com.lefpadlog.financeapp.ui.screen.developer

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DeveloperButton(
    title: String,
    onAction: () -> Unit
) {
    Button(
        content = { Text(text = title) },
        onClick = onAction,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.height(60.dp).padding(top = 10.dp, start = 10.dp)
    )
}
