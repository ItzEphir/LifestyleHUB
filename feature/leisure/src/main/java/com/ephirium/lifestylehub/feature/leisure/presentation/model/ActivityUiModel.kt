package com.ephirium.lifestylehub.feature.leisure.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ActivityUiModel(
    val id: String,
    val name: String,
    val details: List<ActivityDetailUiModel>,
    val notes: List<String>,
) : Parcelable
