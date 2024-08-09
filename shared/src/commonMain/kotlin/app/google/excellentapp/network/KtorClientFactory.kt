package app.google.excellentapp.network

import io.ktor.client.HttpClient

expect class KtorClientFactory() {
    fun create(): HttpClient
}
