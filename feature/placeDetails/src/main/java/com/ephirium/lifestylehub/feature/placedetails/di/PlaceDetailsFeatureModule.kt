package com.ephirium.lifestylehub.feature.placedetails.di

import com.ephirium.lifestylehub.feature.placedetails.presentation.viewmodel.PlaceViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val placeDetailsFeatureModule = module {
    
    viewModelOf(::PlaceViewModel)
}