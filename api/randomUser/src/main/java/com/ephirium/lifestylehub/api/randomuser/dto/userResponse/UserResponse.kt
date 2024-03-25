package com.ephirium.lifestylehub.api.randomuser.dto.userResponse

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val userName: String,
    val photoUrl: String,
)