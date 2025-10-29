package com.todos.app.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// ui/components/Layout.kt
@Composable
fun TwoPaneMasterDetail(
    listPane: @Composable () -> Unit,
    detailPane: @Composable () -> Unit
) {
    Row(Modifier.fillMaxSize()) {
        Box(Modifier.weight(1f)) { listPane() }
        HorizontalDivider(
            Modifier.fillMaxHeight().width(1.dp),
            DividerDefaults.Thickness,
            DividerDefaults.color
        )
        Box(Modifier.weight(1.4f)) { detailPane() }
    }
}
