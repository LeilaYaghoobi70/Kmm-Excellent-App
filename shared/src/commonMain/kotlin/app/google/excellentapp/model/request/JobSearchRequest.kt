package app.google.excellentapp.model.request

data class JobSearchRequest(
    val keywords: String,
    val locationId: String,
    val datePosted: String,
    val sort: String,
)
