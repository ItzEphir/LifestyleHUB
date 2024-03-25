package com.ephirium.lifestylehub.feature.leisure.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActivityDetailUiModel(
    val name: String,
) : Parcelable
