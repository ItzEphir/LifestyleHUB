package com.ephirium.lifestylehub.feature.recommendations.di

import com.ephirium.lifestylehub.feature.recommendations.presentation.viewmodel.RecommendationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val recommendationsFeatureModule = module {
    
    viewModelOf(::RecommendationsViewModel)
}