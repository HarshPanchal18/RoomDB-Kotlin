package com.example.roomdb_kotlin.navigation

import androidx.navigation.NavHostController
import com.example.roomdb_kotlin.util.Action
import com.example.roomdb_kotlin.util.Constants.LIST_SCREEN
import com.example.roomdb_kotlin.util.Constants.SPLASH_SCREEN

class Screens(navController: NavHostController) {
    val splash: () -> Unit = {
        navController.navigate(route = "list/${Action.NOT_ACTION}") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

    val list: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/${taskId}")
    }

    val task: (Action) -> Unit = { action ->
        navController.navigate(route = "list/$action") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }

}
