package com.ephirium.lifestylehub.domain.usecases

import com.ephirium.lifestylehub.domain.model.leisure.Activity
import com.ephirium.lifestylehub.domain.repositories.local.LeisureLocalRepository

class PostLeisureUseCase(private val leisureLocalRepository: LeisureLocalRepository) {
    suspend operator fun invoke(activity: Activity) = leisureLocalRepository.postLeisure(activity)
}