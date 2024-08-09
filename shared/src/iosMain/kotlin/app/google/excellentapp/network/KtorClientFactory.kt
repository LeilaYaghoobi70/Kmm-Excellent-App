package app.google.excellentapp.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual class KtorClientFactory {
    actual fun create(): HttpClient {
        return HttpClient(Darwin) {
            install(HttpTimeout) {
                requestTimeoutMillis = 30000
                connectTimeoutMillis = 30000
                socketTimeoutMillis = 30000
            }

            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.INFO
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                    },
                )
            }
        }
    }
}
