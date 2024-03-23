package com.ephirium.lifestylehub.api.places.dto.placeResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class PlaceDto(
    @SerialName("fsq_id") val id: String,
    val name: String,
    val location: Location,
    val categories: List<Category>,
    @Transient val cursor: String? = null
)
