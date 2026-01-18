package com.chunlunlin.codingtest.di

import com.chunlunlin.codingtest.data.repository_implementation.GithubRepositoryImpl
import com.chunlunlin.codingtext.domain.repository.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindGithubRepository(githubRepository: GithubRepositoryImpl): GithubRepository
}