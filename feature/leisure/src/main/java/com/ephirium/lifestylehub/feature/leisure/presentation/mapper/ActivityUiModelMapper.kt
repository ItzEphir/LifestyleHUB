package com.ephirium.lifestylehub.feature.leisure.presentation.mapper

import com.ephirium.lifestylehub.domain.model.leisure.Activity
import com.ephirium.lifestylehub.domain.model.leisure.ActivityDetail
import com.ephirium.lifestylehub.feature.leisure.presentation.model.ActivityDetailUiModel
import com.ephirium.lifestylehub.feature.leisure.presentation.model.ActivityUiModel

fun Activity.toUi() = ActivityUiModel(
    id = id, name = name, details = details.map { it.toUi() }, notes = notes
)

fun ActivityUiModel.toDomain(userLogin: String) = Activity(
    id = id,
    userLogin = userLogin,
    name = name,
    details = details.map { it.toDomain() },
    notes = notes
)

internal fun ActivityDetail.toUi() = ActivityDetailUiModel(
    name = name
)

internal fun ActivityDetailUiModel.toDomain() = ActivityDetail(
    name = name
)