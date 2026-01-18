package com.chunlunlin.codingtest.ui.main.user_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunlunlin.codingtext.domain.entity.GithubUserDetailEntity
import com.chunlunlin.codingtext.domain.use_case.GetGithubUserDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
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
            emit(UserDetailUiState.Error("Missing user info"))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserDetailUiState.Loading
        )
    } else {
        getGithubUserDetailUseCase(userLogin)
            .map<GithubUserDetailEntity, UserDetailUiState> { UserDetailUiState.Success(it) }
            .onStart { emit(UserDetailUiState.Loading) }
            .catch { emit(UserDetailUiState.Error(it.message ?: "Error")) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UserDetailUiState.Loading
            )
    }
}

sealed class UserDetailUiState {
    object Loading : UserDetailUiState()
    data class Success(val user: GithubUserDetailEntity) : UserDetailUiState()
    data class Error(val message: String) : UserDetailUiState()
}