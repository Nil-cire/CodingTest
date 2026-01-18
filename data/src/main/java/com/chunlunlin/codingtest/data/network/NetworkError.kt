package com.chunlunlin.codingtest.data.network

import com.chunlunlin.codingtext.domain.use_case.UseCaseException

sealed class NetworkError {
    object Network : NetworkError()
    object Timeout : NetworkError()
    object Unauthorized : NetworkError()
    object NotFound : NetworkError()
    data class Unknown(val errorMessage: String?) : NetworkError()
}

fun NetworkError.toUseCaseException(): UseCaseException {
    return when (this) {
        NetworkError.Network -> UseCaseException.Network
        NetworkError.Timeout -> UseCaseException.Timeout
        NetworkError.Unauthorized -> UseCaseException.Unauthorized
        NetworkError.NotFound -> UseCaseException.NotFound
        is NetworkError.Unknown -> UseCaseException.Unknown(this.errorMessage)
    }
}