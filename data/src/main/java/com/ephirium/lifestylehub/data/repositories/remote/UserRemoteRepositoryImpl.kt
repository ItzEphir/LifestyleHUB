package com.ephirium.lifestylehub.data.repositories.remote

import com.ephirium.lifestylehub.api.randomuser.service.UserService
import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.map
import com.ephirium.lifestylehub.data.mappers.toUser
import com.ephirium.lifestylehub.domain.model.user.User
import com.ephirium.lifestylehub.domain.repositories.remote.UserRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRemoteRepositoryImpl(private val userService: UserService) : UserRemoteRepository {
    override suspend fun getUser(login: String, password: String): Flow<ResponseResult<User>> =
        flow {
            emit(userService.getUser().map { it.toUser(login) })
        }.flowOn(Dispatchers.IO)
}