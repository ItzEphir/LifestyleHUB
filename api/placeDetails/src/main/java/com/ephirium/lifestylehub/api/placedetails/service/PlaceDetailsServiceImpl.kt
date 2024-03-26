package com.ephirium.lifestylehub.api.placedetails.service

import com.ephirium.lifestylehub.api.placedetails.dto.PlaceDetails
import com.ephirium.lifestylehub.api.placedetails.dto.placeDetailsReponse.PlaceDetailsResponse
import com.ephirium.lifestylehub.api.placedetails.mapper.toPlaceDetails
import com.ephirium.lifestylehub.common.HttpRoutes
import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.ResponseResult.*
import com.ephirium.lifestylehub.common.ResponseResult.HttpResponse.*
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.*
import io.ktor.client.request.get
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit.MILLISECONDS

class PlaceDetailsServiceImpl(private val httpClient: HttpClient, private val apiKey: String) :
    PlaceDetailsService {
    override suspend fun getPlaceDetails(
        id: String,
        languageCode: String,
    ): ResponseResult<PlaceDetails> = try {
        Ok(httpClient.get("${HttpRoutes.FOURSQUARE_PLACES}/$id") {
            url {
                headers["Authorization"] = apiKey
                headers["Accept-Language"] = languageCode
            }
            timeout {
                requestTimeoutMillis = 5.seconds.toLong(MILLISECONDS)
            }
        }.body<PlaceDetailsResponse>().toPlaceDetails())
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