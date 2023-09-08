package com.example.roomdb_kotlin.ui.screens.task

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.roomdb_kotlin.data.models.Priority
import com.example.roomdb_kotlin.data.models.ToDoTask
import com.example.roomdb_kotlin.ui.viewmodel.SharedViewModel
import com.example.roomdb_kotlin.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority
}
