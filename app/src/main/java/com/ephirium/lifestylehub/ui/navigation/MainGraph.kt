package com.ephirium.lifestylehub.ui.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ephirium.lifestylehub.androidBase.navigation.NavigationRoutes
import com.ephirium.lifestylehub.androidBase.navigation.ScreenProvider
import com.ephirium.lifestylehub.androidBase.navigation.composable
import com.ephirium.lifestylehub.feature.placedetails.ui.screen.PlaceDetailsScreen
import com.ephirium.lifestylehub.feature.profile.ui.screens.ChoosingScreen
import com.ephirium.lifestylehub.feature.profile.ui.screens.SignInScreen
import com.ephirium.lifestylehub.feature.profile.ui.screens.SignUpScreen

fun NavGraphBuilder.mainGraph(navController: NavController, screenProvider: ScreenProvider): Unit {
    composable(navController, screenProvider.home)
    composable(navController, screenProvider.leisure)
    navigation(
        route = screenProvider.profile.route,
        startDestination = NavigationRoutes.ProfileRoutes.CHOOSING,
    ) {
        composable(NavigationRoutes.ProfileRoutes.CHOOSING) {
            ChoosingScreen(
                onSignInClick = {
                    navController.navigate(NavigationRoutes.ProfileRoutes.SIGN_IN)
                },
                onSignUpClick = {
                    navController.navigate(NavigationRoutes.ProfileRoutes.SIGN_UP)
                },
            )
        }
        composable(NavigationRoutes.ProfileRoutes.SIGN_IN) {
            SignInScreen(onSuccessfulSignIn = {
                navController.popBackStack(
                    route = NavigationRoutes.ProfileRoutes.CHOOSING,
                    inclusive = false,
                )
            })
        }
        
        composable(NavigationRoutes.ProfileRoutes.SIGN_UP) {
            SignUpScreen(onSuccessfulSignUp = {
                navController.popBackStack(
                    route = NavigationRoutes.ProfileRoutes.CHOOSING,
                    inclusive = false,
                )
            })
        }
    }
    composable(
        NavigationRoutes.RECOMMENDATION_MASK,
        arguments = listOf(navArgument("recommendation_id") { type = NavType.StringType })
    ) { navBackStackEntry ->
        navBackStackEntry.arguments?.getString("recommendation_id")?.let { id ->
            PlaceDetailsScreen(id = id)
        }
    }
}