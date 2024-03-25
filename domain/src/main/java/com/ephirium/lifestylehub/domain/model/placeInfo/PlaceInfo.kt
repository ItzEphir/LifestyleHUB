package com.ephirium.lifestylehub.domain.model.placeInfo

data class PlaceInfo(
    val id: String,
    val name: String,
    val languageCode: String,
    val categories: List<String>,
    val dateClosed: String?,
    val description: String,
    val email: String?,
    val telephone: String?,
    val link: String?,
    val address: String,
    val hours: List<Hours>?,
    val photos: List<String>,
)