package com.example.roomdb_kotlin.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.roomdb_kotlin.R
import com.example.roomdb_kotlin.components.DisplayAlertDialog
import com.example.roomdb_kotlin.data.models.ToDoTask
import com.example.roomdb_kotlin.ui.theme.topAppBarBackgroundColor
import com.example.roomdb_kotlin.ui.theme.topAppBarContentColor
import com.example.roomdb_kotlin.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    if (selectedTask == null)
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    else
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(navigateToListScreen: (Action) -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.add_task),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        modifier = Modifier.background(color = MaterialTheme.colors.topAppBarBackgroundColor),
        navigationIcon = { BackAction(onBackClicked = navigateToListScreen) },
        actions = { AddAction(onAddClicked = navigateToListScreen) }
    )
}

@Composable
fun BackAction(onBackClicked: (Action) -> Unit) {
    IconButton(onClick = { onBackClicked(Action.NOT_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun AddAction(onAddClicked: (Action) -> Unit) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add_task),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun ExistingTaskAppBar(selectedTask: ToDoTask, navigateToListScreen: (Action) -> Unit) {
    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(
            id = R.string.delete_task,
            selectedTask.title
        ),
        message = stringResource(
            id = R.string.delete_task_confirmation,
            selectedTask.title
        ),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = { navigateToListScreen(Action.DELETE) })

    DeleteAction(onDeleteClicked = navigateToListScreen)
    UpdateAction(onUpdateClicked = navigateToListScreen)
}

@Composable
fun UpdateAction(onUpdateClicked: (Action) -> Unit) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(onDeleteClicked: (Action) -> Unit) {
    IconButton(onClick = { onDeleteClicked(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun CloseAction(onCloseClicked: (Action) -> Unit) {
    IconButton(onClick = { onCloseClicked(Action.NOT_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}
