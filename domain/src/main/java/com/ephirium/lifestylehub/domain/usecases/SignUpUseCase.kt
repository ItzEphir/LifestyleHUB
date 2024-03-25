package com.ephirium.lifestylehub.domain.usecases

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.ResponseResult.Ok
import com.ephirium.lifestylehub.domain.repositories.local.UserLocalRepository
import com.ephirium.lifestylehub.domain.repositories.remote.UserRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class SignUpUseCase(
    private val userRemoteRepository: UserRemoteRepository,
    private val userLocalRepository: UserLocalRepository,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(login: String, password: String) =
        userRemoteRepository.getUser(login, password).flatMapLatest { responseResult ->
            when (responseResult) {
                is Ok -> userLocalRepository.postUser(responseResult.data, password).map {
                    if (it == null) ResponseResult.Error(NullPointerException("User can not be post"))
                    else Ok(it)
                }
                
                else  -> flowOf(responseResult)
            }
        }
}