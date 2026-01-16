package com.chunlunlin.codingtest.data.repository_implementation

import com.chunlunlin.codingtest.data.data_source.GithubDataSource
import com.chunlunlin.codingtest.data.mapper.toEntity
import com.chunlunlin.codingtest.data.network.NetworkResult
import com.chunlunlin.codingtest.data.network.toUseCaseException
import com.chunlunlin.codingtext.domain.entity.GithubUserEntity
import com.chunlunlin.codingtext.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val remoteDataSource: GithubDataSource
) : GithubRepository {

    override fun getUsers(): Flow<List<GithubUserEntity?>> =
        remoteDataSource.getUsers()
            .map { result ->
                when (result) {
                    is NetworkResult.Success ->
                        result.data.map { it.toEntity() }

                    is NetworkResult.Error ->
                        throw result.error.toUseCaseException()
                }
            }

    override fun getUserDetail(login: String): Flow<GithubUserEntity?> =
        remoteDataSource.getUserDetail(login)
            .map { result ->
                when (result) {
                    is NetworkResult.Success ->
                        result.data.toEntity()

                    is NetworkResult.Error ->
                        throw result.error.toUseCaseException()
                }
            }
}