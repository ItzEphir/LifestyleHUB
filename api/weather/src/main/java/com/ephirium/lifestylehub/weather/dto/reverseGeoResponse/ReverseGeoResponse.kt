package com.ephirium.lifestylehub.weather.dto.reverseGeoResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReverseGeoResponse(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    @SerialName("local_names") val localNames: Map<String, String?>
)