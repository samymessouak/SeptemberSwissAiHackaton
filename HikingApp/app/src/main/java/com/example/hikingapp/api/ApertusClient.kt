package com.example.hikingapp.network

import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

data class Message(val role: String, val content: String)

class ApertusClient(private val apiKey: String) {
    private val client = OkHttpClient()
    private val gson = Gson()
    private val baseUrl =
        "https://api.swisscom.com/layer/swiss-ai-weeks/apertus-70b/v1/chat/completions"

    // Store conversation in memory
    private val conversation = mutableListOf(
        Message("system", "You are Apertus, the official AI assistant developed for the Swiss AI Weeks hackathon. You always introduce yourself as \"Apertus\" at the beginning of a new conversation. Your role is to help users explore hikes in Switzerland.\n - When asked general questions, respond politely and briefly introduce yourself again. \n - When asked about hikes, you should provide details from the Swiss hiking context: location, \n" + "difficulty, description, and possible risks. - If a hike is not in the internal list, provide a reasonable guess and mention it clearly. - Always keep your answers practical, structured, and user-friendly. Try to use less then 3 sentences to answer except if it is asked explicitely. When a user ask for what to do, you can redirect the user to our ML Model to verify the conditions, but always propose the best hikes known in switzerland. ")
    )

    fun addUserMessage(text: String) {
        conversation.add(Message("user", text))
    }

    fun sendConversation(callback: (String) -> Unit) {
        val jsonBody = gson.toJson(
            mapOf(
                "model" to "swiss-ai/Apertus-70B",
                "messages" to conversation
            )
        )

        val request = Request.Builder()
            .url(baseUrl)
            .post(jsonBody.toRequestBody("application/json".toMediaType()))
            .header("Authorization", "Bearer $apiKey")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback("Error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val json = gson.fromJson(body, JsonObject::class.java)
                val reply = json["choices"]
                    ?.asJsonArray?.get(0)
                    ?.asJsonObject?.get("message")
                    ?.asJsonObject?.get("content")
                    ?.asString ?: "No reply"

                // Save assistant reply
                conversation.add(Message("assistant", reply))
                callback(reply)
            }
        })
    }
}
