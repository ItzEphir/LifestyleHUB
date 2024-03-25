package com.ephirium.lifestylehub.feature.profile.di

import com.ephirium.lifestylehub.feature.profile.presentation.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authFeatureModule = module {
    viewModelOf(::AuthViewModel)
}