package com.chunlunlin.codingtext.domain.use_case

import com.chunlunlin.codingtext.domain.repository.GithubRepository

class GetGithubUsersUseCase(
    private val repository: GithubRepository
) {
    operator fun invoke() = repository.getUsers()
}