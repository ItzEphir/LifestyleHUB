package com.ephirium.lifestylehub.api.randomuser.di

import com.ephirium.lifestylehub.api.randomuser.service.UserService
import com.ephirium.lifestylehub.api.randomuser.service.UserServiceImpl
import org.koin.core.qualifier.qualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val randomUserApiModule = module {
    single {
        UserServiceImpl(get(), get(qualifier("random_user_api_key")))
    } bind UserService::class
}