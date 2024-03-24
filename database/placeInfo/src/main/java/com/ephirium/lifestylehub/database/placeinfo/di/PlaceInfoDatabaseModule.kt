package com.ephirium.lifestylehub.database.placeinfo.di

import com.ephirium.lifestylehub.database.placeinfo.datastore.PlaceInfoDataStore
import com.ephirium.lifestylehub.database.placeinfo.datastore.PlaceInfoDataStoreImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val placeInfoDatabaseModule = module {
    singleOf(::PlaceInfoDataStoreImpl) bind PlaceInfoDataStore::class
}