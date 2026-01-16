package com.chunlunlin.codingtest.data.data_source

import com.chunlunlin.codingtest.data.dto.GithubUserDto
import com.chunlunlin.codingtest.data.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GithubDataSource {
    fun getUsers(): Flow<NetworkResult<List<GithubUserDto>>>
    fun getUserDetail(login: String): Flow<NetworkResult<GithubUserDto>>
}