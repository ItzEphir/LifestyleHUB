package com.ephirium.lifestylehub.domain.di

import com.ephirium.lifestylehub.domain.usecases.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetCurrentWeatherUseCase)
    
    factoryOf(::GetRecommendationsUseCase)
    
    factoryOf(::GetPlaceInfoUseCase)
    
    factoryOf(::SignInUseCase)
    
    factoryOf(::SignUpUseCase)
    
    factoryOf(::GetCurrentUserUseCase)
    
    factoryOf(::GetLeisureUseCase)
    
    factoryOf(::PostLeisureUseCase)
}