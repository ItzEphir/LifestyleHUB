package com.ephirium.lifestylehub.common

object HttpRoutes {
    private const val OPEN_WEATHER_MAP = "https://api.openweathermap.org"
    const val OPEN_WEATHER_MAP_DATA = "$OPEN_WEATHER_MAP/data/2.5/weather"
    const val OPEN_WEATHER_MAP_GEO = "$OPEN_WEATHER_MAP/geo/1.0"
    
    private const val FOURSQUARE = "https://api.foursquare.com"
    const val FOURSQUARE_PLACES = "$FOURSQUARE/v3/places"
    const val FOURSQUARE_PLACES_SEARCH = "$FOURSQUARE_PLACES/search"
}