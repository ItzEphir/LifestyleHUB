package com.ephirium.lifestylehub.api.placedetails.dto.placeDetailsReponse

import kotlinx.serialization.Serializable

@Serializable
data class Hours(
    val close: String,
    val day: Int,
    val open: String,
)
