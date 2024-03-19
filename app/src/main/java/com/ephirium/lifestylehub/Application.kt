package com.ephirium.lifestylehub

import android.app.Application
import com.ephirium.lifestylehub.feature.currentweather.di.currentWeatherFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin()
    }
    
    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(currentWeatherFeatureModule)
        }
    }
}