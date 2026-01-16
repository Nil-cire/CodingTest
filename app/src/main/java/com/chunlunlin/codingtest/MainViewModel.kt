package com.chunlunlin.codingtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunlunlin.codingtext.domain.use_case.GetGithubUserDetailUseCase
import com.chunlunlin.codingtext.domain.use_case.GetGithubUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGithubUsersUseCase: GetGithubUsersUseCase,
    private val getGithubUserDetailUseCase: GetGithubUserDetailUseCase
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            getGithubUsersUseCase()
                .catch {
                    println("sss error: ${it.message}, ${it.cause}, ${it::class.java.simpleName}")
                }.collect {
                    // handle the result
                    println("sss: ${it.size}")
                }
        }
    }

    fun hi() {}
}