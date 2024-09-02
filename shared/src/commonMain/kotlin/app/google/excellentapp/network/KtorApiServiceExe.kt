package app.google.excellentapp.network
import app.google.excellentapp.model.response.BaseResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.*
import io.ktor.util.InternalAPI

@OptIn(InternalAPI::class)
suspend inline fun <reified D> ApiServiceImp.getApiCall(
    endpoints: String,
    body: Any,
): BaseResponse<D> {
    return try {
        client.get(baseUrl + endpoints) {
            this.body = body
        }.call.response.body<BaseResponse<D>>()
    } catch (e: Exception) {
        e.handleErrors()
    }
}

fun <D> Exception.handleErrors(): BaseResponse<D> {
    return when (this) {
        is ClientRequestException -> {
            BaseResponse(
                code = this.response.status.value,
                message = message,
                data = null,
            )
        }
        else -> {
            BaseResponse(
                code = 400,
                message = message,
                data = null,
            )
        }
    }
}
