package com.chunlunlin.codingtext.domain.use_case

import com.chunlunlin.codingtext.domain.repository.GithubRepository

class GetGithubUserDetailUseCase constructor(
    private val repository: GithubRepository
) {
    operator fun invoke(login: String) = repository.getUserDetail(login)
}