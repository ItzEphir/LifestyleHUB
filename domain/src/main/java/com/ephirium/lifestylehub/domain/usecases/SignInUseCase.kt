package com.ephirium.lifestylehub.domain.usecases

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.ResponseResult.Ok
import com.ephirium.lifestylehub.domain.repositories.local.UserLocalRepository
import kotlinx.coroutines.flow.map

class SignInUseCase(private val userLocalRepository: UserLocalRepository) {
    suspend operator fun invoke(login: String, password: String) =
        userLocalRepository.getUser(login, password).map {
            if(it == null){
                ResponseResult.Error(NullPointerException("User was not found"))
            } else{
                Ok(it)
            }
        }
}