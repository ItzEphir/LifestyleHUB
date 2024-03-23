package test.java.com.ephirium.lifestylehub.data

import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.ResponseResult.Ok
import com.ephirium.lifestylehub.weather.dto.weatherResponse.Main
import com.ephirium.lifestylehub.weather.dto.weatherResponse.Weather
import com.ephirium.lifestylehub.weather.dto.weatherResponse.WeatherResponse
import com.ephirium.lifestylehub.weather.service.WeatherService
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

object MockWeatherService : WeatherService {
    
    // Weather for Moscow lat and lon
    override suspend fun getWeather(
        lat: Float,
        lon: Float,
        lang: String,
    ): ResponseResult<WeatherResponse> {
        delay(1.seconds)
        return Ok(
            data = WeatherResponse(
                main = Main(
                    feelsLike = 277.85,
                    temp = 278.68,
                    tempMin = 275.95,
                    tempMax = 280.44
                ),
                weather = listOf(
                    Weather(
                        id = 804,
                        icon = "04d",
                        description = "пасмурно",
                        main = "Clouds"
                    )
                )
            )
        )
    }
    
}