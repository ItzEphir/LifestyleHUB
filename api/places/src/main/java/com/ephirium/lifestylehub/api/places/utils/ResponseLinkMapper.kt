package com.ephirium.lifestylehub.api.places.utils

fun String?.mapResponseLink() = when (this) {
    null -> null
    else -> {
        val start = indexOf('<') + 1
        val end = indexOf('>')
        slice(start ..< end)
    }
}