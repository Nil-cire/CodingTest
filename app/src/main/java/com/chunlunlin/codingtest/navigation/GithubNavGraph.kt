package com.chunlunlin.codingtest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chunlunlin.codingtest.ui.main.user_list.UserListScreen

@Composable
fun GithubNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.UserList
    ) {
        composable<ScreenRoute.UserList> {
            UserListScreen(
                onUserClick = { user ->
                    // todo: navigation to detail page
                }
            )
        }
    }
}