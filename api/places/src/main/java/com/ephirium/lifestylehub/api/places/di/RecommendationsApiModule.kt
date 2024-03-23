package com.ephirium.lifestylehub.api.places.di

import com.ephirium.lifestylehub.api.places.service.PlacePhotoService
import com.ephirium.lifestylehub.api.places.service.PlacePhotoServiceImpl
import com.ephirium.lifestylehub.api.places.service.PlaceService
import com.ephirium.lifestylehub.api.places.service.PlaceServiceImpl
import org.koin.core.qualifier.qualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val recommendationsApiModule = module {
    single {
        PlaceServiceImpl(get(), get(qualifier("places_api_key")))
    } bind PlaceService::class
    
    single {
        PlacePhotoServiceImpl(get(), get(qualifier("places_api_key")))
    } bind PlacePhotoService::class
}