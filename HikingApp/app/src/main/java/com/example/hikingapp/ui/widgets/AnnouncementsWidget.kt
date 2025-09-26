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
fun AnnouncementsWidget() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp)
    ) {
        Text(
            text = "Announcements",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("📢 Meeting at 3 PM in the office")
        Text("📢 Hiking trip scheduled for next weekend")
    }
}
