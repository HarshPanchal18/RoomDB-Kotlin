package com.example.roomdb_kotlin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.roomdb_kotlin.ui.screens.list.ListScreen
import com.example.roomdb_kotlin.ui.screens.splash.SplashScreen
import com.example.roomdb_kotlin.ui.screens.task.TaskScreen
import com.example.roomdb_kotlin.ui.viewmodel.SharedViewModel
import com.example.roomdb_kotlin.util.Action
import com.example.roomdb_kotlin.util.Constants.LIST_ARGUMENT_KEY
import com.example.roomdb_kotlin.util.Constants.LIST_SCREEN
import com.example.roomdb_kotlin.util.Constants.SPLASH_SCREEN
import com.example.roomdb_kotlin.util.Constants.TASK_ARGUMENT_KEY
import com.example.roomdb_kotlin.util.Constants.TASK_SCREEN
import com.example.roomdb_kotlin.util.toAction

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) { Screens(navController = navController) }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        splashComposable(navigateToListScreen = screen.splash)
        listComposable(navigateToTaskScreen = screen.list, sharedViewModel = sharedViewModel)
        taskComposable(navigateToListScreen = screen.task, sharedViewModel = sharedViewModel)
    }
}

// Destinations
fun NavGraphBuilder.splashComposable(navigateToListScreen: () -> Unit) {
    composable(route = SPLASH_SCREEN) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) { type = NavType.StringType })
    ) { navBackStackEntry ->

        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }

        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel
        )

    }
}

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) { type = NavType.StringType })
    ) { navBackStackEntry ->

        val taskId = navBackStackEntry.arguments?.getInt(TASK_ARGUMENT_KEY)
        LaunchedEffect(key1 = taskId) {
            taskId?.let { sharedViewModel.getSelectedTask(taskId = it) }
        }

        val selectedTask by sharedViewModel.selectedTask.collectAsState()
        LaunchedEffect(key1 = selectedTask) {
            if (selectedTask != null || taskId == -1) {
                sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }
        }

        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel,
            navigateToListScreen = navigateToListScreen
        )
    }
}
