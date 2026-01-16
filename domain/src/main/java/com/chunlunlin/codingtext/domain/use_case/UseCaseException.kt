package com.chunlunlin.codingtext.domain.use_case

sealed class UseCaseException : Throwable() {

    object Network : UseCaseException()

    object Unauthorized : UseCaseException()

    object NotFound : UseCaseException()

    object Timeout : UseCaseException()

    data class Unknown(val errorMessage: String?) : UseCaseException()
}