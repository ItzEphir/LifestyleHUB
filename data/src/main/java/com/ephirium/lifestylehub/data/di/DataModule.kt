package com.ephirium.lifestylehub.data.di

import com.ephirium.lifestylehub.api.placedetails.di.placeDetailsApiModule
import com.ephirium.lifestylehub.api.places.di.recommendationsApiModule
import com.ephirium.lifestylehub.api.randomuser.di.randomUserApiModule
import com.ephirium.lifestylehub.data.createHttpClient
import com.ephirium.lifestylehub.data.repositories.local.LeisureLocalRepositoryImpl
import com.ephirium.lifestylehub.data.repositories.local.PlaceInfoLocalRepositoryImpl
import com.ephirium.lifestylehub.data.repositories.local.UserLocalRepositoryImpl
import com.ephirium.lifestylehub.data.repositories.remote.*
import com.ephirium.lifestylehub.data.repositories.remote.PlacesRemoteRepositoryImpl
import com.ephirium.lifestylehub.database.leisure.di.leisureDatabaseModule
import com.ephirium.lifestylehub.database.placeinfo.di.placeInfoDatabaseModule
import com.ephirium.lifestylehub.database.users.di.usersDatabaseModule
import com.ephirium.lifestylehub.domain.repositories.local.LeisureLocalRepository
import com.ephirium.lifestylehub.domain.repositories.local.PlaceInfoLocalRepository
import com.ephirium.lifestylehub.domain.repositories.local.UserLocalRepository
import com.ephirium.lifestylehub.domain.repositories.remote.*
import com.ephirium.lifestylehub.weather.di.weatherApiModule
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    includes(weatherApiModule, recommendationsApiModule, placeDetailsApiModule, randomUserApiModule)
    
    includes(placeInfoDatabaseModule, usersDatabaseModule, leisureDatabaseModule)
    
    singleOf(::WeatherRemoteRepositoryImpl) bind WeatherRemoteRepository::class
    
    singleOf(::PlacesRemoteRepositoryImpl) bind PlacesRemoteRepository::class
    
    singleOf(::PlaceInfoRemoteRepositoryImpl) bind PlaceInfoRemoteRepository::class
    
    singleOf(::PlaceInfoLocalRepositoryImpl) bind PlaceInfoLocalRepository::class
    
    singleOf(::UserRemoteRepositoryImpl) bind UserRemoteRepository::class
    
    singleOf(::UserLocalRepositoryImpl) bind UserLocalRepository::class
    
    singleOf(::LeisureLocalRepositoryImpl) bind LeisureLocalRepository::class
    
    single {
        createHttpClient()
    } bind HttpClient::class
}