package com.ephirium.lifestylehub.feature.placedetails.presentation.viewmodel

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.lifestylehub.common.onOk
import com.ephirium.lifestylehub.domain.usecases.GetPlaceInfoUseCase
import com.ephirium.lifestylehub.feature.placedetails.presentation.state.PlaceUiState
import com.ephirium.lifestylehub.feature.placedetails.presentation.state.PlaceUiState.Loading
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlaceViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getPlaceInfo: GetPlaceInfoUseCase,
) : ViewModel() {
    val uiState: StateFlow<PlaceUiState> = savedStateHandle.getStateFlow(UI_STATE_KEY, Loading)
    
    fun load(id: String, languageCode: String) {
        viewModelScope.launch {
            getPlaceInfo(id, languageCode).collect {responseResult ->
                responseResult.onOk {
                    savedStateHandle[UI_STATE_KEY] =
                }
            }
        }
    }
    
    companion object {
        const val UI_STATE_KEY = "PlaceUiState"
    }
}