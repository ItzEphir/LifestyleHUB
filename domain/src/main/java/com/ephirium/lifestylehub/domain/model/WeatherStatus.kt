package com.ephirium.lifestylehub.domain.model

import com.ephirium.lifestylehub.domain.model.WeatherStatus.*
import com.ephirium.lifestylehub.domain.model.WeatherStatus.Clouds.*
import com.ephirium.lifestylehub.domain.model.WeatherStatus.Rain.*

sealed interface WeatherStatus {
    val description: String
    
    data class Thunderstorm(override val description: String) : WeatherStatus
    
    data class Drizzle(override val description: String) : WeatherStatus
    
    sealed class Rain(override val description: String) : WeatherStatus {
        class Common(description: String) : Rain(description)
        
        data object Freezing : Rain("freezing rain")
        
        class Shower(description: String) : Rain(description)
    }
    
    data class Snow(override val description: String) : WeatherStatus
    
    data class Atmosphere(override val description: String) : WeatherStatus
    
    data object Clear : WeatherStatus{
        override val description = "clear sky"
    }
    
    sealed class Clouds(override val description: String) : WeatherStatus {
        data object Few: Clouds("few clouds")
        
        data object Scattered: Clouds("scattered clouds")
        
        data object Broken: Clouds("broken clouds")
        
        data object Overcast: Clouds("overcast clouds")
    }
}

fun fromString(statusString: String, description: String): WeatherStatus {
    println("$statusString $description")
    return when(statusString){
        "Thunderstorm" -> Thunderstorm(description)
        "Drizzle" -> Drizzle(description)
        "Rain" -> when(description){
            in listOf("light rain", "moderate rain", "heavy intensity rain", "very heavy rain", "extreme rain") -> Common(description)
            "freezing rain" -> Freezing
            in listOf("light intensity shower rain", "shower rain", "heavy intensity shower rain", "ragged shower rain") -> Shower(description)
            else -> throw IllegalArgumentException("Illegal Rain")
        }
        "Snow" -> Snow(description)
        "Atmosphere" -> Atmosphere(description)
        "Clear" -> Clear
        "Clouds" -> when(description){
            "few clouds" -> Few
            "scattered clouds" -> Scattered
            "broken clouds" -> Broken
            "overcast clouds" -> Overcast
            else -> throw IllegalArgumentException("Illegal Clouds")
        }
        else -> throw IllegalArgumentException("Illegal status")
    }
}
