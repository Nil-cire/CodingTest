package com.chunlunlin.codingtext.domain.entity

data class GithubUserEntity(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val htmlUrl: String,
    val type: String,
    val isSiteAdmin: Boolean,

    // profile
    val profileUrl: String,

    // activities / navigation
    val reposUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val eventsUrl: String
)