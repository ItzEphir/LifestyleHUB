package com.ephirium.lifestylehub.api.placedetails.dto.placeDetailsReponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDetailsResponse(
    @SerialName("fsq_id") val id: String,
    val name: String,
    val categories: List<Category>,
    @SerialName("date_closed") val dateClosed: String? = null,
    val description: String,
    val email: String? = null,
    val tel: String? = null,
    @SerialName("hours_popular") val hours: List<Hours>? = null,
    val link: String? = null,
    val location: Location,
    val photos: List<Photo>,
)