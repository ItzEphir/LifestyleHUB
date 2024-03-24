package com.ephirium.lifestylehub.androidBase.navigation

object NavigationRoutes {
    const val HOME = "home"
    const val LEISURE = "leisure"
    const val PROFILE = "profile"
    private const val RECOMMENDATION = "recommendation"
    const val RECOMMENDATION_MASK = "$RECOMMENDATION/{recommendation_id}"
    
    fun recommendation(id: String) = "$RECOMMENDATION/$id"
}