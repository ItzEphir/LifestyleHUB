package com.ephirium.lifestylehub.api.placedetails.dto.placeDetailsReponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("formatted_address") val address: String,
)
