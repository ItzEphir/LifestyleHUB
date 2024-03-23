package com.ephirium.lifestylehub.api.places.service

import com.ephirium.lifestylehub.api.places.dto.placeResponse.PlacesBodyResponse
import com.ephirium.lifestylehub.api.places.dto.placeResponse.PlacesHeaderResponse
import com.ephirium.lifestylehub.api.places.dto.placeResponse.PlacesResponse
import com.ephirium.lifestylehub.api.places.utils.mapResponseLink
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

internal class PlaceServiceImpl(private val httpClient: HttpClient, private val apiKey: String) :
    PlaceService {
    override suspend fun getPlaces(
        lat: Float,
        lon: Float,
        limit: Int
    ): ResponseResult<PlacesResponse> = try {
        val response = httpClient.get(HttpRoutes.FOURSQUARE_PLACES_SEARCH) {
            url {
                parameters["ll"] = "$lat,$lon"
                parameters["fields"] = "fsq_id,name,location,categories,distance"
                parameters["limit"] = limit.toString()
                parameters["sort"] = "DISTANCE"
                headers["Authorization"] = apiKey
                headers["Accept-Language"] = "ru"
            }
            timeout {
                requestTimeoutMillis = 5.seconds.toLong(MILLISECONDS)
            }
        }
        Ok(
            PlacesResponse(
                headerResponse = PlacesHeaderResponse(
                    link = response.headers["link"].mapResponseLink()
                ),
                bodyResponse = response.body<PlacesBodyResponse>(),
            )
        )
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
    
    override suspend fun getPlacesPage(
        lat: Float,
        lon: Float,
        page: String?,
        perPage: Int,
    ): ResponseResult<PlacesResponse> {
        if (page == null) {
            return getPlaces(lat, lon, perPage)
        }
        return try {
            val response = httpClient.get(HttpRoutes.FOURSQUARE_PLACES_SEARCH) {
                url {
                    parameters["cursor"] = page
                    parameters["ll"] = "$lat,$lon"
                    parameters["fields"] = "fsq_id,name,location,categories,distance"
                    parameters["limit"] = perPage.toString()
                    headers["Authorization"] = apiKey
                    headers["Accept-Language"] = "ru"
                }
                timeout {
                    requestTimeoutMillis = 5.seconds.toLong(MILLISECONDS)
                }
            }
            Ok(
                PlacesResponse(
                    headerResponse = PlacesHeaderResponse(response.headers["link"].mapResponseLink()),
                    bodyResponse = PlacesBodyResponse(results = response.body<PlacesBodyResponse>().results.map { placeDto ->
                        placeDto.copy(
                            cursor = page
                        )
                    })
                )
            )
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
}