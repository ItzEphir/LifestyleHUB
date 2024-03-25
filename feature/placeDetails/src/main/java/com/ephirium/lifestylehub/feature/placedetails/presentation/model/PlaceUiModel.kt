package com.ephirium.lifestylehub.feature.placedetails.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaceUiModel(
    val id: String,
    val name: String,
    val categories: List<String>,
    val dateClosed: String?,
    val description: String,
    val email: String?,
    val telephone: String?,
    val website: String?,
    val address: String,
    val hours: List<Hours>?,
    val photos: List<String>,
) : Parcelable {
    @Parcelize
    data class Hours(
        val open: String,
        val close: String,
        val day: Int,
    ) : Parcelable
}
