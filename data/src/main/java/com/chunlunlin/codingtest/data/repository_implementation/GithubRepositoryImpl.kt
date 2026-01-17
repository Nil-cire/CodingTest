package com.chunlunlin.codingtest.data.repository_implementation

import com.chunlunlin.codingtest.data.data_source.GithubDataSource
import com.chunlunlin.codingtest.data.mapper.toEntity
import com.chunlunlin.codingtest.data.network.NetworkResult
import com.chunlunlin.codingtest.data.network.toUseCaseException
import com.chunlunlin.codingtext.domain.entity.GithubUserEntity
import com.chunlunlin.codingtext.domain.repository.GithubRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val remoteDataSource: GithubDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : GithubRepository {

    override fun getUsers(): Flow<List<GithubUserEntity>> =
        remoteDataSource.getUsers()
            .map { result ->
                when (result) {
                    is NetworkResult.Success ->
                        result.data.map { it.toEntity() }.mapNotNull { it }

                    is NetworkResult.Error ->
                        throw result.error.toUseCaseException()
                }
            }
            .flowOn(ioDispatcher)

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