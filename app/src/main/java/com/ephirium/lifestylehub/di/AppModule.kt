package com.ephirium.lifestylehub.di

import com.ephirium.lifestylehub.BuildConfig
import com.ephirium.lifestylehub.feature.currentweather.di.currentWeatherFeatureModule
import com.ephirium.lifestylehub.feature.recommendations.di.recommendationsFeatureModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module{
    includes(currentWeatherFeatureModule, recommendationsFeatureModule)
    
    single(named("weather_api_key")){
        BuildConfig.WEATHER_API_KEY
    }
    
    single(named("places_api_key")){
        BuildConfig.PLACES_API_KEY
    }
}