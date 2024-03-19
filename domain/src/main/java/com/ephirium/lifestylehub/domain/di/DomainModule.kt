package com.ephirium.lifestylehub.domain.di

import com.ephirium.lifestylehub.domain.usecase.GetCurrentWeatherUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetCurrentWeatherUseCase)
}