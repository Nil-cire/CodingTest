package com.chunlunlin.codingtest.ui.main.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunlunlin.codingtext.domain.entity.GithubUserEntity
import com.chunlunlin.codingtext.domain.use_case.GetGithubUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getGithubUsersUseCase: GetGithubUsersUseCase
) : ViewModel() {

    private val fetchUsersRetryTrigger = MutableSharedFlow<Unit>(replay = 1).apply { tryEmit(Unit) }

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState = fetchUsersRetryTrigger.flatMapLatest {
        getGithubUsersUseCase()
            .map<List<GithubUserEntity>, UserListUiState> { UserListUiState.Success(it) }
            .onStart { emit(UserListUiState.Loading) }
            .catch { emit(UserListUiState.Error(it.message ?: "Error")) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UserListUiState.Loading
    )

    fun fetchUsers() {
        fetchUsersRetryTrigger.tryEmit(Unit)
    }
}

sealed interface UserListUiState {
    object Loading : UserListUiState
    data class Success(val users: List<GithubUserEntity>) : UserListUiState
    data class Error(val message: String) : UserListUiState
}