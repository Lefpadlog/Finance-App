package com.lefpadlog.financeapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun FormTextField(
    label: String,
    value: MutableState<String>,
    valid: Boolean,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    height: Int = 70,
    leaningIcon: ImageVector = Icons.Default.Edit,
    keyboardType: KeyboardType = KeyboardType.Uri,
    onValueChange: (String) -> Unit = { value.value = it }
) {
    OutlinedTextField(
        label = { Text(text = label) },
        value = value.value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        enabled = enabled,
        isError = !valid,
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .background(MaterialTheme.colorScheme.surface),
        leadingIcon = {
            Icon(
                imageVector = leaningIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.offset(y = if (!singleLine) (-72).dp else 0.dp)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}