package com.example.roomdb_kotlin.ui.screens.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.roomdb_kotlin.R
import com.example.roomdb_kotlin.ui.viewmodel.SharedViewModel
import com.example.roomdb_kotlin.util.Action
import com.example.roomdb_kotlin.util.SearchAppBarState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {

    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val action by sharedViewModel.action

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState
    val searchedTasks by sharedViewModel.searchedTask.collectAsState()

    val sortState by sharedViewModel.sortState.collectAsState()
    val lowPriorityTasks by sharedViewModel.lowPriorityTask.collectAsState()
    val highPriorityTasks by sharedViewModel.highPriorityTask.collectAsState()

    val scaffoldState = rememberScaffoldState()

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseAction = { sharedViewModel.handleDatabaseActions(action = action) },
        onUndoClicked = { sharedViewModel.action.value = it },
        taskTitle = sharedViewModel.title.value,
        action = action
    )
    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        floatingActionButton = { ListFAB(onFabClicked = navigateToTaskScreen) }
    ) {
        it
        ListContent(
            allTasks = allTasks,
            searchedTasks = searchedTasks,
            searchAppBarState = searchAppBarState,
            sortState = sortState,
            lowPriorityTasks = lowPriorityTasks,
            highPriorityTasks = highPriorityTasks,
            navigateToTaskScreen = navigateToTaskScreen
        )
    }
}

@Composable
fun ListFAB(onFabClicked: (taskId: Int) -> Unit) {
    FloatingActionButton(
        onClick = { onFabClicked(-1) }
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseAction: () -> Unit,
    onUndoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action
) {

    handleDatabaseAction()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NOT_ACTION) {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = setMessage(action = action, taskTitle = taskTitle),
                    actionLabel = setActionLabel(action = action)
                )
                undoDeletedTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
    }
}

fun undoDeletedTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE)
        onUndoClicked(Action.UNDO)
}

private fun setMessage(action: Action, taskTitle: String): String {
    return when (action) {
        Action.DELETE_ALL -> "All tasks removed"
        else -> "${action.name}: $taskTitle"
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") "UNDO" else "OK"
}
