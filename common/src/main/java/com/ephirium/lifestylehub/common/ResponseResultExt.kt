package com.ephirium.lifestylehub.common

import com.ephirium.lifestylehub.common.ResponseResult.Ok

/**
 * Change result data
 *
 * @param T type of first result
 * @param R type of second result
 * @param block map block
 * @receiver result with type T
 * @return Result with type R
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T, reified R> ResponseResult<T>.map(block: (T) -> R) =
    when (this) {
        is Ok -> Ok(block(data))
        else  -> this as ResponseResult<R>
    }

/**
 * Map element from result to another result
 *
 * @param T type of first result
 * @param R type of second result
 * @param block mapper
 * @receiver Result with type T
 * @return Result with type R
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T, reified R> ResponseResult<T>.flatMap(block: (T) -> ResponseResult<R>) =
    when (this) {
        is Ok -> block(data)
        else  -> this as ResponseResult<R>
    }

/**
 * Run code if result is ok
 *
 * Can be written as:
 *
 * ``result.onOk { println("Successful $it") }``
 *
 * @param T type of result
 * @param block code to run
 * @receiver Result of type T
 * @return Same Result
 */
inline fun <reified T> ResponseResult<T>.onOk(block: (T) -> Unit): ResponseResult<T> {
    if (this is Ok) block(this.data)
    return this
}

/**
 * Run code if result is type T
 *
 * Can be written as:
 *
 * ``result.on<HttpError> { println(it.code) }``
 *
 *
 * @param T type to match
 * @param block code to run
 * @receiver Result with any type
 * @return Same Result
 */
inline fun <reified T : ResponseResult<*>> ResponseResult<*>.on(block: (T) -> Unit): ResponseResult<*> {
    if (this is T) block(this)
    return this
}