package com.chunlunlin.codingtest.data.data_source

import com.chunlunlin.codingtest.data.dto.GithubUserDetailDto
import com.chunlunlin.codingtest.data.dto.GithubUserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiServer {

    @GET("users")
    suspend fun getUsers(): List<GithubUserDto>

    @GET("users/{login}")
    suspend fun getUserDetail(
        @Path("login") login: String
    ): GithubUserDetailDto
}