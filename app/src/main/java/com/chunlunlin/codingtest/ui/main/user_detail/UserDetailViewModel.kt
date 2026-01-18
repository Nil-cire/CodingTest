package com.chunlunlin.codingtest.ui.main.user_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunlunlin.codingtest.ui.main.core.UiState
import com.chunlunlin.codingtest.ui.main.utils.collectAsUiState
import com.chunlunlin.codingtext.domain.use_case.GetGithubUserDetailUseCase
import com.chunlunlin.codingtext.domain.use_case.UseCaseException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getGithubUserDetailUseCase: GetGithubUserDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val userLogin = savedStateHandle.get<String>("login")

    val uiState = if (userLogin == null) {
        flow {
            emit(UiState.Error(UseCaseException.Unknown("Missing user login")))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading
        )
    } else {
        getGithubUserDetailUseCase(userLogin)
            .collectAsUiState(
                transform = { UiState.Success(it) },
                initialValue = UiState.Loading,
                onError = { UiState.Error(it) }
            )
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiState.Loading
            )
    }
}