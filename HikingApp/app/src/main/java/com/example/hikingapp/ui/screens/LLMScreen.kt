package com.example.hikingapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hikingapp.ui.viewmodel.LLMMessageModel
import com.example.hikingapp.ui.viewmodel.LLMViewModel

@Composable
fun LLMScreen(
    modifier: Modifier = Modifier,
    viewModel: LLMViewModel = LLMViewModel()
) {
    Column(modifier = modifier.fillMaxSize()) {
        MessageList(
            modifier = Modifier.weight(1f),
            messageList = viewModel.messageList
        )
        MessageInput { viewModel.sendMessage(it) }
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier, messageList: List<LLMMessageModel>) {
    if (messageList.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = "Default Background",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = "How may I help?",
                style = MaterialTheme.typography.headlineSmall
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            reverseLayout = true
        ) {
            items(messageList.reversed()) { message ->
                MessageRow(messageModel = message)
            }
        }
    }
}

@Composable
fun MessageRow(messageModel: LLMMessageModel) {
    val isModel = messageModel.role == "model"

    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .align((if (isModel) Alignment.Start else Alignment.End) as Alignment.Vertical)
                .padding(
                    start = if (isModel) 8.dp else 70.dp,
                    end = if (isModel) 70.dp else 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
                .clip(RoundedCornerShape(24.dp))
                .background(
                    if (isModel) MaterialTheme.colorScheme.tertiaryContainer
                    else MaterialTheme.colorScheme.primary
                )
                .padding(12.dp)
        ) {
            SelectionContainer {
                Text(
                    text = messageModel.message,
                    fontWeight = FontWeight.Medium,
                    color = if (isModel) MaterialTheme.colorScheme.onTertiaryContainer
                    else MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun MessageInput(onMessageSend: (String) -> Unit) {
    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            placeholder = { Text("Type your message...") },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(24.dp),
            trailingIcon = {
                IconButton(onClick = {
                    if (message.isNotBlank()) {
                        onMessageSend(message)
                        message = ""
                    }
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Send"
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLLMScreen() {
    LLMScreen()
}
