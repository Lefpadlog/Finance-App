package com.lefpadlog.financeapp.ui.screen.graph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lefpadlog.financeapp.code.graph.Graph

@Composable
fun LeftText(graph: Graph) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(5.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "%.2f€".format(graph.maxAmount)
        )

        Text(
            modifier = Modifier.align(Alignment.Start),
            text = "%.2f€".format(graph.minAmount)
        )
    }

}