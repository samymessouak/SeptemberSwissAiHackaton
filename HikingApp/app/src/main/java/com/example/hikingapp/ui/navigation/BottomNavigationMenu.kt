package com.example.hikingapp.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.viewinterop.AndroidView
import com.etebarian.meowbottomnavigation.MeowBottomNavigation

@Composable
fun BottomNavigationMenu(
    onTabSelect: (TopLevelDestination) -> Unit,
    navigationActions: NavigationActions,
    tabList: List<TopLevelDestination>,
    getBottomBarId: (String) -> Int,
) {
    val selectedTabId = remember { mutableIntStateOf(1) } // default tab id
    val currentRoute by navigationActions.currentRoute.collectAsState()
    val bottomNavigation = remember { mutableStateOf<MeowBottomNavigation?>(null) }

    AndroidView(
        factory = { ctx ->
            MeowBottomNavigation(ctx).apply {
                bottomNavigation.value = this

                // Add menu items
                tabList.forEachIndexed { index, tab ->
                    val model = MeowBottomNavigation.Model(index + 1, tab.iconRes)
                    add(model)
                }

                circleColor = 0xFF05A9FB.toInt()          // Blue indicator
                backgroundBottomColor = 0xFFFFFFFF.toInt() // White background
                defaultIconColor = 0xFF4D4D4D.toInt()      // Dark gray (instead of light gray)
                selectedIconColor = 0xFF000000.toInt()     // Black selected

                // ✅ Both listeners are required to avoid NPE
                setOnClickMenuListener { model ->
                    val selectedTab = tabList.getOrNull(model.id - 1)
                    if (selectedTab != null) {
                        onTabSelect(selectedTab)
                    }
                }

                setOnShowListener { model ->
                    Log.d("MeowBottomNavigation", "Tab shown: ${model.id}")
                }

                setOnReselectListener { model ->
                    Log.d("MeowBottomNavigation", "Reselected tab id: ${model.id}")
                }

                contentDescription = "MeowBottomNavigation"
            }
        },
        modifier = Modifier.fillMaxWidth().testTag("BottomNavMenu")
    )

    // Sync selection with NavigationActions
    LaunchedEffect(currentRoute, bottomNavigation.value) {
        val selectedItemId = getBottomBarId(navigationActions.currentRoute())
        selectedTabId.intValue = selectedItemId
        bottomNavigation.value?.let { view ->
            if (view.models.isNotEmpty()) {
                try {
                    view.show(selectedItemId, true)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
