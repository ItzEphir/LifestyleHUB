package com.ephirium.lifestylehub.weather.service

import com.ephirium.lifestylehub.common.HttpRoutes
import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.ResponseResult.*
import com.ephirium.lifestylehub.common.ResponseResult.HttpResponse.*
import com.ephirium.lifestylehub.weather.dto.reverseGeoResponse.ReverseGeoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.*
import io.ktor.client.request.get
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit.MILLISECONDS

internal class GeoServiceImpl(private val client: HttpClient, private val apiKey: String) : GeoService {
    override suspend fun getCityName(
        lat: Float,
        lon: Float,
    ): ResponseResult<ReverseGeoResponse> = try {
        Ok(client.get("${HttpRoutes.OPEN_WEATHER_MAP_GEO}/reverse"){
            url{
                parameters["lat"] = lat.toString()
                parameters["lon"] = lon.toString()
                parameters["appid"] = apiKey
                parameters["limit"] = 1.toString()
            }
            timeout {
                requestTimeoutMillis = 5.seconds.toLong(MILLISECONDS)
            }
        }.body<List<ReverseGeoResponse>>()[0])
    } catch (e: RedirectResponseException) {
        Redirection(e.response.status.value)
    } catch (e: ClientRequestException) {
        ClientError(e.response.status.value)
    } catch (e: ServerResponseException) {
        ServerError(e.response.status.value)
    } catch (e: Exception) {
        when (e) {
            is HttpRequestTimeoutException,
            is ConnectTimeoutException,
            is SocketTimeoutException,
                 -> TimeoutError(e)
            
            else -> Error(e)
        }
    }
}