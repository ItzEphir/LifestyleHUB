package com.ephirium.lifestylehub.data.utils

import java.net.URL

fun String.getCursorFromUrl(): String? {
    val parsedUrl = URL(this)
    val query = parsedUrl.query
    val queryParams = query.split("&").associate {
        val (key, value) = it.split("=")
        key to value
    }
    
    return queryParams["cursor"]
}