package com.chunlunlin.codingtest.ui.main.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunlunlin.codingtest.ui.main.core.UiState
import com.chunlunlin.codingtest.ui.main.utils.collectAsUiState
import com.chunlunlin.codingtext.domain.use_case.GetGithubUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
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
            .collectAsUiState(
                transform = { UiState.Success(it) },
                initialValue = UiState.Loading,
                onError = { UiState.Error(it) }
            )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState.Loading
    )

    fun fetchUsers() {
        fetchUsersRetryTrigger.tryEmit(Unit)
    }
}

//sealed interface UserListUiState {
//    object Loading : UserListUiState
//    data class Success(val users: List<GithubUserEntity>) : UserListUiState
//    data class Error(val error: UseCaseException) : UserListUiState
//}