package com.lefpadlog.financeapp.ui.screen.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lefpadlog.financeapp.code.graph.Graph
import com.lefpadlog.financeapp.ui.screen.graph.graphcomponents.drawAmountZeroLine
import com.lefpadlog.financeapp.ui.screen.graph.graphcomponents.drawGraph

@Composable
fun GraphFrame(graph: Graph) {

    if (graph.entries.isEmpty())
        return

    Column {
        TopText(graph)

        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            LeftText(graph)

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(7.dp)
            ) {
                if (graph.minAmount <= 0)
                    drawAmountZeroLine(graph)
                drawGraph(graph)
            }

        }
    }

}