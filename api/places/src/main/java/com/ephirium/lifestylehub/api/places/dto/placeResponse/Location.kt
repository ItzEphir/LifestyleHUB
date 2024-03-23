package com.ephirium.lifestylehub.api.places.dto.placeResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("country") val country: String,
    @SerialName("address") val address: String? = null,
    @SerialName("address_extended") val addressExtended: String? = null,
)
