package com.chunlunlin.codingtest.data.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RetryInterceptor : Interceptor {

    private val maxRetry = 3

    override fun intercept(chain: Interceptor.Chain): Response {
        var attempt = 0
        var response: Response? = null
        var exception: IOException? = null

        while (attempt < maxRetry) {
            try {
                response = chain.proceed(chain.request())
                if (response.isSuccessful) return response
            } catch (e: IOException) {
                exception = e
            }
            attempt++
        }

        exception?.let { throw it }
        return response ?: throw IOException("Unknown network error")
    }
}