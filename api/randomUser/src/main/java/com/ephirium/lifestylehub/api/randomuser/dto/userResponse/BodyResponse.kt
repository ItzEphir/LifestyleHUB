package com.ephirium.lifestylehub.api.randomuser.dto.userResponse

import kotlinx.serialization.Serializable

@Serializable
data class BodyResponse(val results: List<UserResponse>)
