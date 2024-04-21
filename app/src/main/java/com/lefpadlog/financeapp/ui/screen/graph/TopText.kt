package com.lefpadlog.financeapp.ui.screen.graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lefpadlog.financeapp.code.date.convertDate
import com.lefpadlog.financeapp.code.graph.Graph

@Composable
fun TopText(graph: Graph) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(5.dp)
    ) {
        Text(
            modifier = Modifier.weight(2f),
            text = convertDate(graph.oldestDate),
            textAlign = TextAlign.End
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "-",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.weight(2f),
            text = convertDate(graph.newestDate),
            textAlign = TextAlign.Start
        )
    }

}