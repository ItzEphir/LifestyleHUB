package com.ephirium.lifestylehub.androidBase.navigation

object NavigationRoutes {
    const val HOME = "home"
    const val LEISURE = "leisure"
    const val PROFILE = "profile"
    
    object ProfileRoutes {
        const val CHOOSING = "choosing"
        const val SIGN_IN = "sign_in"
        const val SIGN_UP = "sign_up"
    }
    
    private const val RECOMMENDATION = "recommendation"
    const val RECOMMENDATION_MASK = "$RECOMMENDATION/{recommendation_id}"
    
    fun recommendation(id: String) = "$RECOMMENDATION/$id"
}