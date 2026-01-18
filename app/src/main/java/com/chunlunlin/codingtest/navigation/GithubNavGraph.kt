package com.chunlunlin.codingtest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.chunlunlin.codingtest.ui.main.user_detail.UserDetailScreen
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
                    navController.navigate(ScreenRoute.UserDetail(login = user.login))
                }
            )
        }

        composable<ScreenRoute.UserDetail> { backStackEntry ->
            val args = backStackEntry.toRoute<ScreenRoute.UserDetail>()
            UserDetailScreen(
                login = args.login,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}