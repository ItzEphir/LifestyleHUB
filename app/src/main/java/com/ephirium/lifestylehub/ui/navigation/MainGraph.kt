package com.ephirium.lifestylehub.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ephirium.lifestylehub.androidBase.navigation.NavigationRoutes
import com.ephirium.lifestylehub.androidBase.navigation.ScreenProvider
import com.ephirium.lifestylehub.androidBase.navigation.composable
import com.ephirium.lifestylehub.feature.placedetails.ui.screen.PlaceDetailsScreen

fun NavGraphBuilder.mainGraph(navController: NavController, screenProvider: ScreenProvider): Unit {
    composable(navController, screenProvider.home)
    composable(navController, screenProvider.leisure)
    composable(navController, screenProvider.profile)
    composable(
        NavigationRoutes.RECOMMENDATION_MASK,
        arguments = listOf(navArgument("recommendation_id") { type = NavType.StringType })
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getString("recommendation_id")?.let { id ->
            PlaceDetailsScreen(id = id)
        }
    }
}