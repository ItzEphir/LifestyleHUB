package com.ephirium.lifestylehub.weather.service

import com.ephirium.lifestylehub.common.HttpRoutes
import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.ResponseResult.*
import com.ephirium.lifestylehub.weather.dto.weatherResponse.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.host
import io.ktor.http.URLProtocol
import io.ktor.http.URLProtocol.Companion
import io.ktor.http.Url
import java.net.URL

internal class WeatherServiceImpl(
    private val client: HttpClient,
) : WeatherService {
    
    override suspend fun getWeather(
        lat: Float,
        lon: Float,
    ): ResponseResult<WeatherResponse> = try {
        Ok(client.get("https://${HttpRoutes.OPEN_WEATHER_MAP_DATA}") {
            url {
                parameters["lat"] = lat.toString()
                parameters["lon"] = lon.toString()
                parameters["appid"] = "dbaad18638759894a816af331cfb5672"
                parameters["units"] = "metric"
            }
        }.body<WeatherResponse>().also{
            println(it)
        })
    } catch (e: RedirectResponseException) {
        Redirection(e.response.status.value)
    } catch (e: ClientRequestException) {
        ClientError(e.response.status.value)
    } catch (e: ServerResponseException) {
        ServerError(e.response.status.value)
    } catch (e: Exception) {
        Error(e)
    }
}