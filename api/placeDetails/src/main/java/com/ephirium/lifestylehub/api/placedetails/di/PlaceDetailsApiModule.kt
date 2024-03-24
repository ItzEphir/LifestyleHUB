package com.ephirium.lifestylehub.api.placedetails.di

import com.ephirium.lifestylehub.api.placedetails.service.PlaceDetailsService
import com.ephirium.lifestylehub.api.placedetails.service.PlaceDetailsServiceImpl
import org.koin.core.qualifier.qualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val placeDetailsApiModule = module {
    single {
        PlaceDetailsServiceImpl(get(), get(qualifier("places_api_key")))
    } bind PlaceDetailsService::class
}