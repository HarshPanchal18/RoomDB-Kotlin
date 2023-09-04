package com.example.roomdb_kotlin.data.models

import androidx.compose.ui.graphics.Color
import com.example.roomdb_kotlin.ui.theme.HighPriorityColor
import com.example.roomdb_kotlin.ui.theme.LowPriorityColor
import com.example.roomdb_kotlin.ui.theme.MediumPriorityColor
import com.example.roomdb_kotlin.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}
