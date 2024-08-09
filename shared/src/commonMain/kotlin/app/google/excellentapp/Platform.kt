package app.google.excellentapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform