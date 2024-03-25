package com.ephirium.lifestylehub.api.randomuser.service

import com.ephirium.lifestylehub.api.randomuser.dto.userResponse.UserResponse
import com.ephirium.lifestylehub.common.ResponseResult

interface UserService {
    suspend fun getUser(): ResponseResult<UserResponse>
}