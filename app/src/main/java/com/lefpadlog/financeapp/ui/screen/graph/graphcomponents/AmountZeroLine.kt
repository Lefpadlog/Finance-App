package com.lefpadlog.financeapp.ui.screen.graph.graphcomponents

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.lefpadlog.financeapp.code.graph.Graph
import com.lefpadlog.financeapp.ui.theme.lightGreyColor

fun DrawScope.drawAmountZeroLine(graph: Graph) {
    drawLine(
        start = Offset(0f, size.height - graph.amountZeroPercentage * size.height),
        end = Offset(size.width, size.height - graph.amountZeroPercentage * size.height),
        color = lightGreyColor,
        strokeWidth = 2f
    )
}