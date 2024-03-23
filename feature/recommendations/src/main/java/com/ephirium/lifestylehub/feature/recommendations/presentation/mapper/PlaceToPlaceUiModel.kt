package com.ephirium.lifestylehub.feature.recommendations.presentation.mapper

import com.ephirium.lifestylehub.domain.model.recommendation.Place
import com.ephirium.lifestylehub.domain.model.recommendation.Recommendation
import com.ephirium.lifestylehub.feature.recommendations.presentation.model.PlaceUiModel
import com.ephirium.lifestylehub.feature.recommendations.presentation.model.RecommendationUiModel

fun Recommendation.toUiModel(): RecommendationUiModel = when (this) {
    is Place -> PlaceUiModel(
        id = id,
        name = name,
        address = address,
        categories = categories.map { it.name },
        photoUrls = placePhotoUrls,
        cursor = cursor,
        nextCursor = nextCursor
    )
}