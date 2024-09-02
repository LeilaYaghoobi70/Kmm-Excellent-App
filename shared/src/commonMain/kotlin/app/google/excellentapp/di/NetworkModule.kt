package app.google.excellentapp.di

import app.google.excellentapp.network.ApiServiceImp
import app.google.excellentapp.network.KtorClientFactory
import io.ktor.client.HttpClient
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class NetworkModule {
    @Single
    fun provideHttpClint() = KtorClientFactory().create()

    @Single
    fun provideBaseUrl() = "https://linkedin-data-api.p.rapidapi.com"

    @Single
    fun provideJobApiService(
        httpClient: HttpClient,
        baseUrl: String,
    ) = ApiServiceImp(baseUrl = baseUrl, client = httpClient)
}
