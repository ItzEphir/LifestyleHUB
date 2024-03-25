package com.ephirium.lifestylehub.database.users.di

import com.ephirium.lifestylehub.database.users.datastore.UsersDatastore
import com.ephirium.lifestylehub.database.users.datastore.UsersDatastoreImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val usersDatabaseModule = module {
    singleOf(::UsersDatastoreImpl) bind UsersDatastore::class
}