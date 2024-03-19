package com.ephirium.lifestylehub.common

sealed interface ResponseResult<in T> {
    
    interface HttpError : ResponseResult<Any> {
        val code: Int
    }
    
    data class Redirection(override val code: Int) : HttpError // 3xx
    
    data class ClientError(override val code: Int) : HttpError // 4xx
    
    data class ServerError(override val code: Int) : HttpError // 5xx
    
    data class Error(val exception: Exception) : ResponseResult<Any>
    
    data class Ok<T>(val data: T) : ResponseResult<T>
}