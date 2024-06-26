package com.ephirium.lifestylehub.di

import com.ephirium.lifestylehub.BuildConfig
import com.ephirium.lifestylehub.androidBase.di.androidBaseModule
import com.ephirium.lifestylehub.androidBase.navigation.ScreenProvider
import com.ephirium.lifestylehub.data.di.dataModule
import com.ephirium.lifestylehub.domain.di.domainModule
import com.ephirium.lifestylehub.feature.profile.di.profileFeatureModule
import com.ephirium.lifestylehub.feature.currentweather.di.currentWeatherFeatureModule
import com.ephirium.lifestylehub.feature.leisure.di.leisureFeatureModule
import com.ephirium.lifestylehub.feature.placedetails.di.placeDetailsFeatureModule
import com.ephirium.lifestylehub.feature.recommendations.di.recommendationsFeatureModule
import com.ephirium.lifestylehub.ui.navigation.ScreenProviderImpl
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    includes(androidBaseModule)
    includes(
        currentWeatherFeatureModule,
        recommendationsFeatureModule,
        placeDetailsFeatureModule,
        profileFeatureModule,
        leisureFeatureModule,
    )
    includes(dataModule, domainModule)
    
    single(named("weather_api_key")) {
        BuildConfig.WEATHER_API_KEY
    }
    
    single(named("places_api_key")) {
        BuildConfig.PLACES_API_KEY
    }
    
    single(named("random_user_api_key")){
        BuildConfig.RANDOM_USER_API_KEY
    }
    
    single {
        ScreenProviderImpl
    } bind ScreenProvider::class
}