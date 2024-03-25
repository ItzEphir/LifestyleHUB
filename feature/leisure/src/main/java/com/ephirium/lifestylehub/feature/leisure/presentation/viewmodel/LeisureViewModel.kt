package com.ephirium.lifestylehub.feature.leisure.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.lifestylehub.domain.model.user.User
import com.ephirium.lifestylehub.domain.usecases.GetCurrentUserUseCase
import com.ephirium.lifestylehub.domain.usecases.GetLeisureUseCase
import com.ephirium.lifestylehub.domain.usecases.PostLeisureUseCase
import com.ephirium.lifestylehub.feature.leisure.presentation.mapper.toDomain
import com.ephirium.lifestylehub.feature.leisure.presentation.mapper.toUi
import com.ephirium.lifestylehub.feature.leisure.presentation.model.ActivityUiModel
import com.ephirium.lifestylehub.feature.leisure.presentation.state.LeisureScreenUiState
import com.ephirium.lifestylehub.feature.leisure.presentation.state.LeisureScreenUiState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LeisureViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getCurrentUser: GetCurrentUserUseCase,
    private val getLeisure: GetLeisureUseCase,
    private val postLeisure: PostLeisureUseCase,
) : ViewModel() {
    
    val uiState: StateFlow<LeisureScreenUiState> =
        savedStateHandle.getStateFlow(UI_STATE_KEY, Loading)
    
    private val userState: MutableStateFlow<User?> = MutableStateFlow(null)
    
    fun loadLeisure() {
        savedStateHandle[UI_STATE_KEY] = Loading
        viewModelScope.launch {
            getCurrentUser().collectLatest { user ->
                if (user == null) {
                    savedStateHandle[UI_STATE_KEY] = NoUser
                    return@collectLatest
                }
                userState.update { user }
                getLeisure(user.login).collectLatest { activities ->
                    savedStateHandle[UI_STATE_KEY] =
                        ActivityList(activities = activities.map { activity ->
                            activity.toUi()
                        })
                }
            }
        }
    }
    
    fun goToActivityEditor() {
        savedStateHandle[UI_STATE_KEY] = AddActivity(
            ActivityUiModel(
                id = "",
                name = "",
                details = emptyList(),
                notes = emptyList(),
            )
        )
    }
    
    fun goToActivities(){
        loadLeisure()
    }
    
    fun submitActivity(activityUiModel: ActivityUiModel){
        viewModelScope.launch {
            userState.value?.let {
                postLeisure(activityUiModel.toDomain(it.login)).collectLatest {
                    loadLeisure()
                }
            }
        }
    }
    
    companion object {
        private const val UI_STATE_KEY = "leisure_ui_state"
    }
}