package com.chunlunlin.codingtest.data.dto

import com.google.gson.annotations.SerializedName

data class GithubUserDto(
    val login: String? = null,
    val id: Int? = null,
    @SerializedName("node_id")
    val nodeId: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("gravatar_id")
    val gravatarId: String? = null,
    val url: String? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    @SerializedName("followers_url")
    val followersUrl: String? = null,
    @SerializedName("following_url")
    val followingUrl: String? = null,
    @SerializedName("gists_url")
    val gistsUrl: String? = null,
    @SerializedName("starred_url")
    val starredUrl: String? = null,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String? = null,
    @SerializedName("organizations_url")
    val organizationsUrl: String? = null,
    @SerializedName("repos_url")
    val reposUrl: String? = null,
    @SerializedName("events_url")
    val eventsUrl: String? = null,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String? = null,
    val type: String? = null,
    @SerializedName("user_view_type")
    val userViewType: String? = null,
    @SerializedName("site_admin")
    val siteAdmin: Boolean? = null
)