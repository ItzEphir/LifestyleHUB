package com.ephirium.lifestylehub.androidBase.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

sealed class Screen(
    val route: String,
    @DrawableRes val iconRes: Int,
    @StringRes val textRes: Int,
    val screen: @Composable (NavController) -> Unit,
) {
    class Home(
        screen: @Composable (NavController) -> Unit,
        @DrawableRes iconRes: Int,
        @StringRes textRes: Int,
    ) : Screen(
        route = NavigationRoutes.HOME, iconRes = iconRes, textRes = textRes, screen = screen,
    )
    
    class Leisure(
        screen: @Composable (NavController) -> Unit,
        @DrawableRes iconRes: Int,
        @StringRes textRes: Int,
    ) : Screen(
        route = NavigationRoutes.LEISURE,
        iconRes = iconRes,
        textRes = textRes,
        screen = screen,
    )
    
    class Profile(
        screen: @Composable (NavController) -> Unit,
        @DrawableRes iconRes: Int,
        @StringRes textRes: Int,
    ) : Screen(
        route = NavigationRoutes.PROFILE,
        iconRes = iconRes,
        textRes = textRes,
        screen = screen,
    )
}