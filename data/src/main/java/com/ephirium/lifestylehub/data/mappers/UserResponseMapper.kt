package com.ephirium.lifestylehub.data.mappers

import com.ephirium.lifestylehub.api.randomuser.dto.userResponse.UserResponse
import com.ephirium.lifestylehub.domain.model.user.User

fun UserResponse.toUser(login: String) = User(
    login = login,
    avatarUrl = photoUrl,
    name=userName
)