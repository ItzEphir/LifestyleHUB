package com.ephirium.lifestylehub.feature.profile.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SigningUiModel(
    val login: String,
    val password: String,
) : Parcelable
