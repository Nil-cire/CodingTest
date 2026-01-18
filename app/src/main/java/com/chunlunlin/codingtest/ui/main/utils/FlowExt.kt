package com.chunlunlin.codingtest.ui.main.utils

import com.chunlunlin.codingtext.domain.use_case.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

fun <T, S> Flow<T>.collectAsUiState(
    transform: (value: T) -> S,
    initialValue: S? = null,
    onError: (e: UseCaseException) -> S,
): Flow<S> = this
    .map { it -> transform(it) }
    .onStart { initialValue?.let { emit(initialValue) } }
    .catch { e ->
        val error = when (e) {
            is UseCaseException -> e
            else -> UseCaseException.Unknown(null)
        }
        emit(onError(error))
    }