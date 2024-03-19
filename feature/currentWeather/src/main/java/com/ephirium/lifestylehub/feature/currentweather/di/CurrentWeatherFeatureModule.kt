package com.ephirium.lifestylehub.feature.currentweather.di

import com.ephirium.lifestylehub.data.di.dataModule
import com.ephirium.lifestylehub.domain.di.domainModule
import com.ephirium.lifestylehub.feature.currentweather.location.DefaultLocationClient
import com.ephirium.lifestylehub.feature.currentweather.location.LocationClient
import com.ephirium.lifestylehub.feature.currentweather.presentation.viewmodel.WeatherViewModel
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val currentWeatherFeatureModule = module {
    includes(domainModule, dataModule)
    
    viewModelOf(::WeatherViewModel)
    
    single{
        DefaultLocationClient(
            androidContext(),
            LocationServices.getFusedLocationProviderClient(androidContext())
        )
    } bind LocationClient::class
}