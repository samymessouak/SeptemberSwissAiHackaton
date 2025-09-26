package com.example.hikingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hikingapp.ui.navigation.BottomNavigationMenu
import com.example.hikingapp.ui.navigation.NavigationActions
import com.example.hikingapp.ui.navigation.TopLevelDestination
import com.example.hikingapp.ui.screens.DashboardScreen
import com.example.hikingapp.ui.screens.LLMScreen
import com.example.hikingapp.ui.screens.ChatMapWithPopupScreen
import com.example.hikingapp.ui.screens.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { HikingApp() }
    }
}

@Composable
fun HikingApp() {
    val navController = rememberNavController()
    val navigationActions = NavigationActions(navController)

    val tabs = listOf(
        TopLevelDestination("dashboard", R.drawable.icon_home, "Home"),
        TopLevelDestination("chat", R.drawable.icon_map, "Chat"),
        TopLevelDestination("llmscreen", R.drawable.profile, "LLM"),
        TopLevelDestination("profile", R.drawable.icon_menu, "Profile")
    )


    Scaffold(
        bottomBar = {
            BottomNavigationMenu(
                onTabSelect = { navigationActions.navigateTo(it) },
                navigationActions = navigationActions,
                tabList = tabs,
                getBottomBarId = { route -> tabs.indexOfFirst { it.route == route } + 1 }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("dashboard") { DashboardScreen() }
            composable("chat") { ChatMapWithPopupScreen() }
            composable("llmscreen") { LLMScreen(BuildConfig.APERTUS_API_KEY) }
            composable("profile") { ProfileScreen() }
        }
    }
}
