package com.ephirium.lifestylehub.weather.di

import com.ephirium.lifestylehub.weather.service.GeoService
import com.ephirium.lifestylehub.weather.service.GeoServiceImpl
import com.ephirium.lifestylehub.weather.service.WeatherService
import com.ephirium.lifestylehub.weather.service.WeatherServiceImpl
import org.koin.core.qualifier.qualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val weatherApiModule = module {
    single{
        WeatherServiceImpl(get(), get(qualifier = qualifier("weather_api_key")))
    } bind WeatherService::class
    
    single{
        GeoServiceImpl(get(), get(qualifier("weather_api_key")))
    } bind GeoService::class
}