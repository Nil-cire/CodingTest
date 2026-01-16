package com.chunlunlin.codingtest.data.data_source

import com.chunlunlin.codingtest.data.common.flowNetworkResult
import com.chunlunlin.codingtest.data.dto.GithubUserDto
import com.chunlunlin.codingtest.data.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRemoteDataSourceImpl @Inject constructor(
    private val githubApi: GithubApiServer
) : GithubDataSource {
    override fun getUsers(): Flow<NetworkResult<List<GithubUserDto>>> =
        flowNetworkResult {
            githubApi.getUsers()
        }

    override fun getUserDetail(login: String): Flow<NetworkResult<GithubUserDto>> =
        flowNetworkResult {
            githubApi.getUserDetail(login)
        }
}