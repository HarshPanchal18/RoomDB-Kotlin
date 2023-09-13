package com.example.roomdb_kotlin.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.roomdb_kotlin.data.models.Priority
import com.example.roomdb_kotlin.ui.theme.Typography

@Composable
fun PriorityItem(priority: Priority) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Canvas(modifier = Modifier.size(16.dp)) {
            drawCircle(color = priority.color)
        }

        Text(
            text = priority.name,
            modifier = Modifier.padding(start = 12.dp),
            style = Typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
