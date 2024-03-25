package com.ephirium.lifestylehub.feature.profile.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUiModel(
    val login: String,
    val name: String,
    val avatar: String,
) : Parcelable
