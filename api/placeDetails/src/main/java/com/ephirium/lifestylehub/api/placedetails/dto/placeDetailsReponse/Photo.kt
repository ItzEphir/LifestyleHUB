package com.ephirium.lifestylehub.api.placedetails.dto.placeDetailsReponse

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val prefix: String,
    val suffix: String,
)
