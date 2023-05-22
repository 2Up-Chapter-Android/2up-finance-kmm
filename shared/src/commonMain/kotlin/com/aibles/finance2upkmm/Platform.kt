package com.aibles.finance2upkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform