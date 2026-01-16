package com.chunlunlin.codingtest.data.mapper

import com.chunlunlin.codingtest.data.dto.GithubUserDto
import com.chunlunlin.codingtext.domain.entity.GithubUserEntity

fun GithubUserDto.toEntity(): GithubUserEntity? {
    return if (login.isNullOrEmpty() || id == null) {
        null
    } else {
        GithubUserEntity(
            login = login,
            id = id,
            avatarUrl = avatarUrl.orEmpty(),
            htmlUrl = htmlUrl.orEmpty(),
            type = type.orEmpty(),
            isSiteAdmin = siteAdmin == true,
            profileUrl = htmlUrl.orEmpty(),
            reposUrl = reposUrl.orEmpty(),
            followersUrl = followersUrl.orEmpty(),
            followingUrl = followingUrl.orEmpty(),
            eventsUrl = eventsUrl.orEmpty()
        )
    }
}