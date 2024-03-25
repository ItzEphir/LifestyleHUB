package com.ephirium.lifestylehub.feature.leisure.di

import com.ephirium.lifestylehub.feature.leisure.presentation.viewmodel.LeisureViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val leisureFeatureModule = module {
    viewModelOf(::LeisureViewModel)
}