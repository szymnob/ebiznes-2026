package com.example

import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val token = System.getenv("DISCORD_TOKEN") 
        ?: error("Brak zmiennej DISCORD_TOKEN!")

    val kord = Kord(token)

    kord.on<MessageCreateEvent> {
        val author = message.author?.username ?: "Unknown"
        val content = message.content
        
        println("Log: Odebrano wiadomość: '$content' od $author")

        if (message.author?.isBot == true) return@on

        // odpowiedz na komendu ping i status
        when (content.lowercase().trim()) {
            "ping" -> {
                println("Log: Wykryto komendę ping, próbuję odpowiedzieć...")
                try {
                    message.channel.createMessage("Bot działa poprawnie")
                    println("Log: Odpowiedź wysłana.")
                } catch (e: Exception) {
                    println("Log: BŁĄD WYSYŁANIA: ${e.message}")
                }
            }
            "status" -> {
                message.channel.createMessage("Wszystkie działa.")
            }
        }
    }

    println("Bot został uruchomiony i nasłuchuje na Discordzie...")

    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
        intents += Intent.GuildMessages
    }
}