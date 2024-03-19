package com.ephirium.lifestylehub.weather.di

import com.ephirium.lifestylehub.weather.service.GeoService
import com.ephirium.lifestylehub.weather.service.GeoServiceImpl
import com.ephirium.lifestylehub.weather.service.WeatherService
import com.ephirium.lifestylehub.weather.service.WeatherServiceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val weatherApiModule = module {
    singleOf(::WeatherServiceImpl) bind WeatherService::class
    
    singleOf(::GeoServiceImpl) bind GeoService::class
}