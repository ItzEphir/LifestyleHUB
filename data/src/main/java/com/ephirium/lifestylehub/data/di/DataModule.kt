package com.ephirium.lifestylehub.data.di

import com.ephirium.lifestylehub.api.places.di.recommendationsApiModule
import com.ephirium.lifestylehub.data.repositories.remote.PlacePhotoRepositoryImpl
import com.ephirium.lifestylehub.data.repositories.remote.PlacesRepositoryImpl
import com.ephirium.lifestylehub.data.repositories.remote.WeatherRepositoryImpl
import com.ephirium.lifestylehub.domain.repositories.remote.PlacePhotoRepository
import com.ephirium.lifestylehub.domain.repositories.remote.PlacesRepository
import com.ephirium.lifestylehub.domain.repositories.remote.WeatherRepository
import com.ephirium.lifestylehub.weather.di.weatherApiModule
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit.MILLISECONDS

val dataModule = module {
    includes(weatherApiModule, recommendationsApiModule)
    
    singleOf(::WeatherRepositoryImpl) bind WeatherRepository::class
    
    singleOf(::PlacesRepositoryImpl) bind PlacesRepository::class
    
    singleOf(::PlacePhotoRepositoryImpl) bind PlacePhotoRepository::class
    
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 5.seconds.toLong(MILLISECONDS)
            }
        }
    } bind HttpClient::class
}