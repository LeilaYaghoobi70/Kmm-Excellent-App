package app.google.excellentapp.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("code")
    val code: Int = 200,
    @SerialName("message")
    val message: String?,
    @SerialName("data")
    val data: T?,
)
