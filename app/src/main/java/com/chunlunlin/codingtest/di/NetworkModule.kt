package com.chunlunlin.codingtest.di

import com.chunlunlin.codingtest.data.data_source.GithubApiServer
import com.chunlunlin.codingtest.data.network.OkHttpProvider
import com.chunlunlin.codingtest.data.network.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpProvider.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        RetrofitProvider.create(okHttpClient)

    @Provides
    @Singleton
    fun provideGithubApi(
        retrofit: Retrofit
    ): GithubApiServer =
        retrofit.create(GithubApiServer::class.java)
}
