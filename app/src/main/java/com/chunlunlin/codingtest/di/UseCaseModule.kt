package com.chunlunlin.codingtest.di

import com.chunlunlin.codingtext.domain.repository.GithubRepository
import com.chunlunlin.codingtext.domain.use_case.GetGithubUserDetailUseCase
import com.chunlunlin.codingtext.domain.use_case.GetGithubUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetGithubUsersUseCase(
        repository: GithubRepository
    ): GetGithubUsersUseCase {
        return GetGithubUsersUseCase(repository)
    }

    @Provides
    fun provideGetGithubUserDetailUseCase(
        repository: GithubRepository
    ): GetGithubUserDetailUseCase {
        return GetGithubUserDetailUseCase(repository)
    }
}