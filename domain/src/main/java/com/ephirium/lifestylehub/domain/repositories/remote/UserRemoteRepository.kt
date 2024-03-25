package com.ephirium.lifestylehub.domain.repositories.remote

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRemoteRepository {
    
    suspend fun getUser(login: String, password: String): Flow<ResponseResult<User>>
}