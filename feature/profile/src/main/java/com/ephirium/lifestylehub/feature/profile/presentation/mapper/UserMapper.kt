package com.ephirium.lifestylehub.feature.profile.presentation.mapper

import com.ephirium.lifestylehub.domain.model.user.User
import com.ephirium.lifestylehub.feature.profile.presentation.model.UserUiModel

fun User.toUi() = UserUiModel(
    login = login,
    name = name,
    avatar = avatarUrl
)