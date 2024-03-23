package com.ephirium.lifestylehub.androidBase.di

import com.ephirium.lifestylehub.androidBase.location.DefaultLocationClient
import com.ephirium.lifestylehub.androidBase.location.LocationClient
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module

val composablesModule = module {
    single{
        DefaultLocationClient(
            androidContext(),
            LocationServices.getFusedLocationProviderClient(androidContext())
        )
    } bind LocationClient::class
}