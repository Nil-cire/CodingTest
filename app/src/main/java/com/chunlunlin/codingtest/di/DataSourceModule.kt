package com.chunlunlin.codingtest.di

import com.chunlunlin.codingtest.data.data_source.GithubDataSource
import com.chunlunlin.codingtest.data.data_source.GithubRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindGithubRemoteDataSource(githubRemoteDataSource: GithubRemoteDataSourceImpl): GithubDataSource
}