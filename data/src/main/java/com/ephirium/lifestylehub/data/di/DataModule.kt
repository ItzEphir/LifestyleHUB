package com.ephirium.lifestylehub.data.di

import com.ephirium.lifestylehub.api.placedetails.di.placeDetailsApiModule
import com.ephirium.lifestylehub.api.places.di.recommendationsApiModule
import com.ephirium.lifestylehub.data.createHttpClient
import com.ephirium.lifestylehub.data.repositories.local.PlaceInfoLocalRepositoryImpl
import com.ephirium.lifestylehub.data.repositories.remote.PlaceInfoRemoteRepositoryImpl
import com.ephirium.lifestylehub.data.repositories.remote.PlacePhotoRepositoryImpl
import com.ephirium.lifestylehub.data.repositories.remote.PlacesRepositoryImpl
import com.ephirium.lifestylehub.data.repositories.remote.WeatherRepositoryImpl
import com.ephirium.lifestylehub.database.placeinfo.di.placeInfoDatabaseModule
import com.ephirium.lifestylehub.domain.repositories.local.PlaceInfoLocalRepository
import com.ephirium.lifestylehub.domain.repositories.remote.PlaceInfoRemoteRepository
import com.ephirium.lifestylehub.domain.repositories.remote.PlacePhotoRepository
import com.ephirium.lifestylehub.domain.repositories.remote.PlacesRepository
import com.ephirium.lifestylehub.domain.repositories.remote.WeatherRepository
import com.ephirium.lifestylehub.weather.di.weatherApiModule
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    includes(weatherApiModule, recommendationsApiModule, placeDetailsApiModule)
    
    includes(placeInfoDatabaseModule)
    
    singleOf(::WeatherRepositoryImpl) bind WeatherRepository::class
    
    singleOf(::PlacesRepositoryImpl) bind PlacesRepository::class
    
    singleOf(::PlacePhotoRepositoryImpl) bind PlacePhotoRepository::class
    
    singleOf(::PlaceInfoRemoteRepositoryImpl) bind PlaceInfoRemoteRepository::class
    
    singleOf(::PlaceInfoLocalRepositoryImpl) bind PlaceInfoLocalRepository::class
    
    single {
        createHttpClient()
    } bind HttpClient::class
}