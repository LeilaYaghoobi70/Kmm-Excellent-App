package app.google.excellentapp.model.response

data class Company(
    val id: Int,
    val name: String,
    val universalName: String,
    val description: String,
    val logo: String,
    val url: String,
    val followerCount: Int,
    val staffCount: Int,
    val staffCountRange: StaffCountRange,
    val specialities: List<String>,
    val industries: List<String>,
    val headquarter: Headquarter,
)
