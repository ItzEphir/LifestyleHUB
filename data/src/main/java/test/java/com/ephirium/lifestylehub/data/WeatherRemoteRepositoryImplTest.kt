package test.java.com.ephirium.lifestylehub.data

import com.ephirium.lifestylehub.common.ResponseResult.Ok
import com.ephirium.lifestylehub.data.repositories.remote.WeatherRemoteRepositoryImpl
import com.ephirium.lifestylehub.domain.model.weather.WeatherInfo
import com.ephirium.lifestylehub.domain.model.weather.WeatherStatus
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WeatherRemoteRepositoryImplTest {
    @Test
    fun getWeatherReturnsCorrectData() = runBlocking {
        val expected = Ok(
            WeatherInfo(
               temperature = 278.68f,
                maxTemperature = 280.44f,
                minTemperature = 275.95f,
                feelsLike = 277.85f,
                status = WeatherStatus(
                    name = "Clouds",
                    description = "пасмурно"
                ),
                city = "Москва",
                iconId = "04d"
            )
        )
        
        val actual = WeatherRemoteRepositoryImpl(MockWeatherService, MockGeoService).getWeatherInfo(
            latitude = 55.751244f,
            longitude = 37.618423f,
            languageCode = "ru"
        ).last()
        
        assert(actual == expected)
    }
}