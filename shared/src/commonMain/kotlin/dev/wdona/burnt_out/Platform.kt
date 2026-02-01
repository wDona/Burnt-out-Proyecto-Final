package dev.wdona.burnt_out

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform