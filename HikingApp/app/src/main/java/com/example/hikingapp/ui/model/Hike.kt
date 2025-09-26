package com.example.hikingapp.ui.model

data class Hike(
    val name: String,
    val lat: Double,
    val lon: Double,
    val description: String,
    var riskLevel: String? = null,
    var insuranceUrl: String? = null,
    var mark: Double? = null // ⭐ Mark between 0.0 and 5.0
)
