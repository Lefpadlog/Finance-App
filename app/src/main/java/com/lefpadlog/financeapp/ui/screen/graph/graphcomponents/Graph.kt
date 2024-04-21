package com.lefpadlog.financeapp.ui.screen.graph.graphcomponents

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.lefpadlog.financeapp.code.graph.Graph
import com.lefpadlog.financeapp.ui.theme.greenColor
import com.lefpadlog.financeapp.ui.theme.redColor

fun DrawScope.drawGraph(graph: Graph) {
    val offsetList = mutableListOf<Offset>()

    for (entry in graph.entries)
        offsetList.add(Offset(entry.key * size.width, size.height - entry.value * size.height))

    if (offsetList.isEmpty())
        return

    var previousOffset: Offset = offsetList[0]
    for (offset in offsetList) {
        drawLine(
            color = if (previousOffset.y > offset.y) greenColor else redColor,
            start = previousOffset,
            end = offset,
            strokeWidth = 5f
        )
        previousOffset = offset
    }

}