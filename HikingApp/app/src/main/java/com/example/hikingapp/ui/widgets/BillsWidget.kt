package com.example.hikingapp.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BillsWidget() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFF9C4))
            .padding(16.dp)
    ) {
        Text(
            text = "Bills",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("💡 Electricity: CHF 45")
        Text("💧 Water: CHF 20")
        Text("🏠 Rent: CHF 1200")
    }
}
