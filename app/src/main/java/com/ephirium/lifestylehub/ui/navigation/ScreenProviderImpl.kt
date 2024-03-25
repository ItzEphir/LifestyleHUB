package com.ephirium.lifestylehub.ui.navigation

import com.ephirium.lifestylehub.R
import com.ephirium.lifestylehub.androidBase.navigation.NavigationRoutes.recommendation
import com.ephirium.lifestylehub.androidBase.navigation.Screen.*
import com.ephirium.lifestylehub.androidBase.navigation.ScreenProvider
import com.ephirium.lifestylehub.feature.leisure.ui.screens.LeisureScreen
import com.ephirium.lifestylehub.feature.recommendations.ui.screen.RecommendationsScreen

object ScreenProviderImpl : ScreenProvider {
    override val home: Home = Home(
        iconRes = R.drawable.home,
        textRes = R.string.home,
        screen = { navController ->
            RecommendationsScreen(onRecommendationClick = {
                navController.navigate(recommendation(it))
            })
        },
    )
    
    override val leisure: Leisure = Leisure(
        iconRes = R.drawable.leisure,
        textRes = R.string.leisure,
        screen = { _ ->
            LeisureScreen()
        },
    )
    
    override val profile: Profile = Profile(
        iconRes = R.drawable.profile,
        textRes = R.string.profile,
        screen = { } // navigation was used
    )
}