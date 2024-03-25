package com.ephirium.lifestylehub.feature.leisure.presentation.state

import android.os.Parcelable
import com.ephirium.lifestylehub.feature.leisure.presentation.model.ActivityUiModel
import kotlinx.parcelize.Parcelize

sealed interface LeisureScreenUiState : Parcelable {
    
    @Parcelize
    data class ActivityList(val activities: List<ActivityUiModel>) : LeisureScreenUiState
    
    @Parcelize
    data object NoUser : LeisureScreenUiState
    
    @Parcelize
    data class AddActivity(val activityUiModel: ActivityUiModel) : LeisureScreenUiState
    
    @Parcelize
    data object Loading : LeisureScreenUiState
    
}