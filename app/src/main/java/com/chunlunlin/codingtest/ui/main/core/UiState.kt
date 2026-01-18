package com.chunlunlin.codingtest.ui.main.core

import com.chunlunlin.codingtext.domain.use_case.UseCaseException

sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val exception: UseCaseException) : UiState<Nothing>
}