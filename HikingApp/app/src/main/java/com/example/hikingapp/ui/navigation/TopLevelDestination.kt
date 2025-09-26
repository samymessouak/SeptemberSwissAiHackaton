package com.example.hikingapp.ui.navigation

import androidx.annotation.DrawableRes

data class TopLevelDestination(
    val route: String,
    @DrawableRes val iconRes: Int,
    val text: String
)
