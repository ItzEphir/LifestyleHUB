package com.ephirium.lifestylehub.feature.leisure.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.ephirium.lifestylehub.domain.model.leisure.ActivityDetail
import com.ephirium.lifestylehub.feature.leisure.presentation.mapper.toUi
import com.ephirium.lifestylehub.feature.leisure.presentation.model.ActivityUiModel
import com.ephirium.lifestylehub.feature.leisure.presentation.viewmodel.LeisureViewModel
import com.ephirium.lifestylehub.feature.leisure.ui.components.ActivityEditor
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun AddActivityScreenShortcut(
    viewModel: LeisureViewModel = koinViewModel(),
    details: List<ActivityDetail>,
    onAdd: () -> Unit,
    onBack: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.loadLeisure()
    }
    
    ActivityEditor(
        activityUiModel = ActivityUiModel(
            id = UUID.randomUUID().toString(),
            name = "",
            details = details.map { it.toUi() },
            notes = emptyList()
        ), onSubmitClick = {
            viewModel.submitActivity(it)
            onAdd()
        }, onBackClick = onBack
    )
}