package com.ephirium.lifestylehub.feature.recommendations.presentation.state

import android.os.Parcelable
import com.ephirium.lifestylehub.feature.recommendations.presentation.model.RecommendationUiModel
import kotlinx.parcelize.Parcelize

sealed interface RecommendationsScreenState : Parcelable {
    
    @Parcelize
    data class Success(val recommendations: List<RecommendationUiModel>) : RecommendationsScreenState
    
    @Parcelize
    data object Loading : RecommendationsScreenState
    
    @Parcelize
    data object LocationDenied : RecommendationsScreenState
    
    @Parcelize
    data object NetworkError : RecommendationsScreenState
    
    @Parcelize
    data object Error : RecommendationsScreenState
}