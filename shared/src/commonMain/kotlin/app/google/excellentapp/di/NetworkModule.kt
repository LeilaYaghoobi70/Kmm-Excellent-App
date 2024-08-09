package app.google.excellentapp.di

import app.google.excellentapp.network.JobApiServiceImp
import app.google.excellentapp.network.KtorClientFactory
import io.ktor.client.HttpClient

class NetworkModule {
    fun provideHtppClint() = KtorClientFactory().create()

    fun provideBaseUrl() = "https://linkedin-data-api.p.rapidapi.com"

    fun provideJobApiService(
        httpClient: HttpClient,
        baseUrl: String,
    ) = JobApiServiceImp(baseUrl = baseUrl, client = httpClient)
}
