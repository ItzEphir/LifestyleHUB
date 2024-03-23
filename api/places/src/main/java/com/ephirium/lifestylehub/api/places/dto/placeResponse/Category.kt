package com.ephirium.lifestylehub.api.places.dto.placeResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("name") val name: String,
    @SerialName("plural_name") val pluralName: String,
)
