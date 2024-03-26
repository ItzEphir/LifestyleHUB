package com.ephirium.lifestylehub.api.randomuser.service

import com.ephirium.lifestylehub.api.randomuser.dto.userResponse.BodyResponse
import com.ephirium.lifestylehub.api.randomuser.dto.userResponse.UserResponse
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

internal class UserServiceImpl(private val httpClient: HttpClient, private val apiKey: String) :
    UserService {
    @Suppress("SpellCheckingInspection")
    override suspend fun getUser(): ResponseResult<UserResponse> = try {
        Ok(httpClient.get("${HttpRoutes.RANDOM_USER}/98v7yepo") {
            url {
                parameters["key"] = apiKey
            }
            timeout {
                requestTimeoutMillis = 5.seconds.toLong(MILLISECONDS)
            }
        }.body<BodyResponse>().results[0])
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