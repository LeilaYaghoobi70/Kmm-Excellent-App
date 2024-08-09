package app.google.excellentapp.network

import app.google.excellentapp.model.request.JobSearchRequest
import app.google.excellentapp.model.response.BaseResponse
import app.google.excellentapp.model.response.JobData

interface JobApiService {
    suspend fun searchJobs(request: JobSearchRequest): BaseResponse<JobData>
}
