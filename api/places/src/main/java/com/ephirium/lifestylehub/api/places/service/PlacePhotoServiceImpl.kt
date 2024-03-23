package com.ephirium.lifestylehub.api.places.service

import com.ephirium.lifestylehub.api.places.dto.placeResponse.PlacePhotoResponse
import com.ephirium.lifestylehub.common.HttpRoutes
import com.ephirium.lifestylehub.common.ResponseResult.*
import com.ephirium.lifestylehub.common.ResponseResult.HttpResponse.*
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class PlacePhotoServiceImpl(private val httpClient: HttpClient, private val apiKey: String) :
    PlacePhotoService {
    override suspend fun getPhotos(id: String) = try {
        CoroutineScope(Dispatchers.IO).async {
            Ok(httpClient.get("${HttpRoutes.FOURSQUARE_PLACES}/$id/photos") {
                url {
                    headers["Authorization"] = apiKey
                    parameters["classifications"] = "outdoor,indoor"
                }
            }.body<List<PlacePhotoResponse>>().map { it.prefix + "original" + it.suffix })
        }.await()
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