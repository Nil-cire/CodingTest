package com.chunlunlin.codingtest.navigation

import kotlinx.serialization.Serializable

sealed class ScreenRoute {
    @Serializable
    object UserList
}