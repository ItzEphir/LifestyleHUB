package com.ephirium.lifestylehub.common

sealed interface ResponseResult<in T> {
    
    sealed interface HttpResponse : ResponseResult<Any> {
        val code: Int
        
        data class Redirection(override val code: Int) : HttpResponse // 3xx
        
        data class ClientError(override val code: Int) : HttpResponse // 4xx
        
        data class ServerError(override val code: Int) : HttpResponse // 5xx
    }
    
    data class TimeoutError(val exception: Exception): ResponseResult<Any>
    
    data class Error(val exception: Exception) : ResponseResult<Any>
    
    data class Ok<T>(val data: T) : ResponseResult<T>
}