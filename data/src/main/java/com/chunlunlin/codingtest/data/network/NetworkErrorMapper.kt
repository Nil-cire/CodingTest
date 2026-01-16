package com.chunlunlin.codingtest.data.network

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object NetworkErrorMapper {

    fun map(throwable: Throwable): NetworkError {
        return when (throwable) {
            is SocketTimeoutException -> NetworkError.Timeout
            is IOException -> NetworkError.Network
            is HttpException -> {
                when (throwable.code()) {
                    401 -> NetworkError.Unauthorized
                    404 -> NetworkError.NotFound
                    else -> NetworkError.Unknown(throwable.message()) // add more cases if needed
                }
            }
            else -> NetworkError.Unknown(throwable.message)
        }
    }
}