package app.google.excellentapp.network

import app.google.excellentapp.model.request.JobSearchRequest
import app.google.excellentapp.model.response.BaseResponse
import app.google.excellentapp.model.response.JobData
import io.ktor.client.*

class JobApiServiceImp(
    val baseUrl: String,
    val client: HttpClient,
) : JobApiService {
    override suspend fun searchJobs(request: JobSearchRequest): BaseResponse<JobData> =
        getApiCall(
            endpoints = "search-jobs",
            body = request,
        )
}
