package com.ephirium.lifestylehub.database.leisure.di

import com.ephirium.lifestylehub.database.leisure.datastore.LeisureDatastore
import com.ephirium.lifestylehub.database.leisure.datastore.LeisureDatastoreImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val leisureDatabaseModule = module {
    singleOf(::LeisureDatastoreImpl) bind LeisureDatastore::class
}