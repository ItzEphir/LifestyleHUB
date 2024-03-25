package com.ephirium.lifestylehub.domain.usecases

import com.ephirium.lifestylehub.domain.repositories.local.LeisureLocalRepository

class GetLeisureUseCase(private val leisureLocalRepository: LeisureLocalRepository) {
    suspend operator fun invoke(userId: String) = leisureLocalRepository.getLeisure(userId)
}