package com.ephirium.lifestylehub.weather.service

import com.ephirium.lifestylehub.common.HttpRoutes
import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.ResponseResult.*
import com.ephirium.lifestylehub.weather.dto.reverseGeoResponse.ReverseGeoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.URLProtocol.Companion
import io.ktor.http.contentType

internal class GeoServiceImpl(private val client: HttpClient) : GeoService {
    override suspend fun getCityName(
        lat: Float,
        lon: Float,
    ): ResponseResult<ReverseGeoResponse> = try {
        Ok(client.get("https://${HttpRoutes.OPEN_WEATHER_MAP_GEO}reverse"){
            url{
                parameters["lat"] = lat.toString()
                parameters["lon"] = lon.toString()
                parameters["appid"] = "dbaad18638759894a816af331cfb5672"
                parameters["limit"] = 1.toString()
            }
        }.body<List<ReverseGeoResponse>>()[0])
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