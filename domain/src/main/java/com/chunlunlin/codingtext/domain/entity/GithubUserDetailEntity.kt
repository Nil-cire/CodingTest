package com.chunlunlin.codingtext.domain.entity

data class GithubUserDetailEntity(
    val login: String,
    val avatarUrl: String,
    val siteAdmin: Boolean,
    val name: String?,
    val company: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    val blog: String?,
    val twitterUsername: String?,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int,
    val htmlUrl: String,
    val createdAt: String,
    val updatedAt: String
)