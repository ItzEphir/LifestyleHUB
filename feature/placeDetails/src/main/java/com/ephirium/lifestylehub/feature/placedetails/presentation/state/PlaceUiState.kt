package com.ephirium.lifestylehub.feature.placedetails.presentation.state

import android.os.Parcelable
import com.ephirium.lifestylehub.feature.placedetails.presentation.model.PlaceUiModel
import kotlinx.parcelize.Parcelize

sealed interface PlaceUiState: Parcelable {
    
    @Parcelize
    data object Loading: PlaceUiState
    
    @Parcelize
    data class Success(val placeUiModel: PlaceUiModel) : PlaceUiState

    @Parcelize
    data object Timeout: PlaceUiState
    
    @Parcelize
    data object HttpError: PlaceUiState
    
    @Parcelize
    data object Error: PlaceUiState
    
}