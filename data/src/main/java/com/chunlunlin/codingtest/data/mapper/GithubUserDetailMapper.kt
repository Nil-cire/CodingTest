package com.chunlunlin.codingtest.data.mapper

import com.chunlunlin.codingtest.data.dto.GithubUserDetailDto
import com.chunlunlin.codingtext.domain.entity.GithubUserDetailEntity

fun GithubUserDetailDto.toEntity(): GithubUserDetailEntity {
    return GithubUserDetailEntity(
        this.login,
        this.avatarUrl,
        this.siteAdmin,
        this.name,
        this.company,
        this.location,
        this.email,
        this.bio,
        this.blog,
        this.twitterUsername,
        this.publicRepos,
        this.publicGists,
        this.followers,
        this.following,
        this.htmlUrl,
        this.createdAt.substringBefore("T"),
        this.updatedAt.substringBefore("T")
    )
}