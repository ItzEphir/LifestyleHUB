package com.ephirium.lifestylehub.feature.recommendations.di

import com.ephirium.lifestylehub.androidBase.di.composablesModule
import com.ephirium.lifestylehub.data.di.dataModule
import com.ephirium.lifestylehub.domain.di.domainModule
import com.ephirium.lifestylehub.feature.recommendations.presentation.viewmodel.RecommendationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val recommendationsFeatureModule = module {
    includes(dataModule, domainModule, composablesModule)
    
    viewModelOf(::RecommendationsViewModel)
}