package com.example.roomdb_kotlin.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.roomdb_kotlin.data.models.Priority
import com.example.roomdb_kotlin.data.models.ToDoTask
import com.example.roomdb_kotlin.ui.viewmodel.SharedViewModel
import com.example.roomdb_kotlin.util.Action

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority
    val context: Context = LocalContext.current

    Scaffold(topBar = {
        TaskAppBar(selectedTask = selectedTask, navigateToListScreen = { action ->
            if (action == Action.NOT_ACTION)
                navigateToListScreen(action)
            else if (sharedViewModel.validateFields())
                navigateToListScreen(action)
            else
                displayToast(context = context)

        })
    }) {
        it
        TaskContent(
            title = title,
            onTitleChange = { sharedViewModel.title.value = it },
            description = description,
            onDescriptionChange = { sharedViewModel.description.value = it },
            priority = priority,
            onPrioritySelected = { sharedViewModel.priority.value = it }
        )
    }
}

fun displayToast(context: Context) {
    Toast.makeText(context, "Fields are empty.", Toast.LENGTH_SHORT).show()
}
