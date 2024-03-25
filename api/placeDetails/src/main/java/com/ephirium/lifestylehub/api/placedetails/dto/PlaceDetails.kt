package com.ephirium.lifestylehub.api.placedetails.dto

import com.ephirium.lifestylehub.api.placedetails.dto.placeDetailsReponse.Hours

data class PlaceDetails(
    val id: String,
    val name: String,
    val categories: List<String>,
    val address: String,
    val description: String,
    val dateClosed: String?,
    val email: String?,
    val telephone: String?,
    val hours: List<Hours>?,
    val website: String?,
    val photos: List<String>,
)