package com.chunlunlin.codingtest.data.common

import com.chunlunlin.codingtest.data.network.NetworkErrorMapper
import com.chunlunlin.codingtest.data.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

inline fun <T> flowNetworkResult(
    crossinline block: suspend () -> T
): Flow<NetworkResult<T>> =
    flow<NetworkResult<T>> {
        emit(NetworkResult.Success(block()))
    }.catch { e ->
        println("sss error: ${e.message}, $e")
        val error = NetworkErrorMapper.map(e)
        emit(NetworkResult.Error(error))
    }