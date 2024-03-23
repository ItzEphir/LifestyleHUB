package com.ephirium.lifestylehub.domain.di

import com.ephirium.lifestylehub.domain.usecases.GetCurrentWeatherUseCase
import com.ephirium.lifestylehub.domain.usecases.GetRecommendationsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetCurrentWeatherUseCase)
    
    factoryOf(::GetRecommendationsUseCase)
}