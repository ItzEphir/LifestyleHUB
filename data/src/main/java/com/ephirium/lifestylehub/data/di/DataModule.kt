package com.ephirium.lifestylehub.data.di

import com.ephirium.lifestylehub.data.repository.WeatherRepositoryImpl
import com.ephirium.lifestylehub.domain.repository.WeatherRepository
import com.ephirium.lifestylehub.weather.di.weatherApiModule
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module{
    includes(weatherApiModule)
    
    singleOf(::WeatherRepositoryImpl) bind WeatherRepository::class
    
    single {
        HttpClient(CIO){
            install(ContentNegotiation){
                json(Json{
                    prettyPrint = true
                    ignoreUnknownKeys = true
                })
            }
        }
    } bind HttpClient::class
}