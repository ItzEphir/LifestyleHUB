package com.ephirium.lifestylehub.feature.placedetails.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.ResponseResult.HttpResponse
import com.ephirium.lifestylehub.common.ResponseResult.TimeoutError
import com.ephirium.lifestylehub.common.on
import com.ephirium.lifestylehub.common.onOk
import com.ephirium.lifestylehub.domain.usecases.GetPlaceInfoUseCase
import com.ephirium.lifestylehub.feature.placedetails.presentation.mapper.toPlaceUi
import com.ephirium.lifestylehub.feature.placedetails.presentation.state.PlaceUiState
import com.ephirium.lifestylehub.feature.placedetails.presentation.state.PlaceUiState.*
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PlaceViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getPlaceInfo: GetPlaceInfoUseCase,
) : ViewModel() {
    val uiState: StateFlow<PlaceUiState> = savedStateHandle.getStateFlow(UI_STATE_KEY, Loading)
    
    fun load(id: String, languageCode: String) {
        viewModelScope.launch {
            getPlaceInfo(id, languageCode).collectLatest {responseResult ->
                responseResult.onOk {
                    savedStateHandle[UI_STATE_KEY] = Success(it.toPlaceUi())
                }.on<HttpResponse>{
                    savedStateHandle[UI_STATE_KEY] = HttpError
                }.on<ResponseResult.Error>{
                    savedStateHandle[UI_STATE_KEY] = Error
                }.on<TimeoutError>{
                    savedStateHandle[UI_STATE_KEY] = Timeout
                }
            }
        }
    }
    
    companion object {
        const val UI_STATE_KEY = "PlaceUiState"
    }
}