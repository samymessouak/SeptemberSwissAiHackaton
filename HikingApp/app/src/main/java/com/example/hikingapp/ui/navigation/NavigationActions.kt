package com.example.hikingapp.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavigationActions(
    private val navController: NavHostController
) {
    private val currentRoute_ = MutableStateFlow("")
    val currentRoute: StateFlow<String> = currentRoute_.asStateFlow()

    fun navigateTo(destination: TopLevelDestination) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
        currentRoute_.value = destination.route
    }

    fun navigateTo(route: String) {
        navController.navigate(route)
        currentRoute_.value = route
    }

    fun goBack() {
        navController.popBackStack()
        currentRoute_.value = navController.currentDestination?.route ?: ""
    }

    fun currentRoute(): String {
        return navController.currentDestination?.route ?: ""
    }
}
