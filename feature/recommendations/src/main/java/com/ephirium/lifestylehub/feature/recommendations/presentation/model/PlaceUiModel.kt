package com.ephirium.lifestylehub.feature.recommendations.presentation.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaceUiModel(
    override val id: String,
    val name: String,
    val address: String,
    val categories: List<String>,
    var photoUrls: List<String>? = null,
    val cursor: String? = null,
    val nextCursor: String? = null,
) : RecommendationUiModel(id)