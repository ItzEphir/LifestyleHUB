package com.ephirium.lifestylehub.domain.usecases

import com.ephirium.lifestylehub.domain.repositories.local.UserLocalRepository

class GetCurrentUserUseCase(private val userLocalRepository: UserLocalRepository) {
    suspend operator fun invoke() = userLocalRepository.getCurrentUser()
}