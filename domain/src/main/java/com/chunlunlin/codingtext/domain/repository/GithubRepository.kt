package com.chunlunlin.codingtext.domain.repository

import com.chunlunlin.codingtext.domain.entity.GithubUserDetailEntity
import com.chunlunlin.codingtext.domain.entity.GithubUserEntity
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    fun getUsers(): Flow<List<GithubUserEntity>>

    fun getUserDetail(login: String): Flow<GithubUserDetailEntity>
}