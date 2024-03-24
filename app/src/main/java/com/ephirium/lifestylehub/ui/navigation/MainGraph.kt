package com.ephirium.lifestylehub.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ephirium.lifestylehub.androidBase.navigation.NavigationRoutes
import com.ephirium.lifestylehub.androidBase.navigation.ScreenProvider
import com.ephirium.lifestylehub.androidBase.navigation.composable

fun NavGraphBuilder.mainGraph(navController: NavController, screenProvider: ScreenProvider): Unit {
    composable(navController, screenProvider.home)
    composable(navController, screenProvider.leisure)
    composable(navController, screenProvider.profile)
    composable(NavigationRoutes.RECOMMENDATION_MASK) {
        
    }
}