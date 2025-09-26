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
fun ChatWidget() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8F5E9))
            .padding(16.dp)
    ) {
        Text(
            text = "Chat",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("👤 Alice: See you tomorrow!")
        Text("👤 Bob: Don’t forget the report")
        Text("👤 Carol: Can we reschedule?")
    }
}
