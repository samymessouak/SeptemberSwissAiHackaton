package com.example.hikingapp.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class LLMMessageModel(
    val message: String,
    val role: String // "user" ou "model"
)

class LLMViewModel : ViewModel() {
    var messageList = mutableStateListOf<LLMMessageModel>()
        private set

    fun sendMessage(userMessage: String) {
        // Ajouter le message utilisateur
        messageList.add(LLMMessageModel(userMessage, "user"))

        // TODO: Appel API vers ton LLM (Apertus/Swisscom)
        val fakeResponse = "Réponse de l'IA à: $userMessage"
        messageList.add(LLMMessageModel(fakeResponse, "model"))
    }
}
