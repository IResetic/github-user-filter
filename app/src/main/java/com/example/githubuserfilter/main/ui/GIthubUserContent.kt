package com.example.githubuserfilter.main.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.githubuserfilter.userdetails.presentation.navigation.UserDetailsScreenDestination
import com.example.githubuserfilter.userdetails.presentation.navigation.userDetailsGraph
import com.example.githubuserfilter.usersfilter.presentation.navigation.GithubUsersScreenNavigation
import com.example.githubuserfilter.usersfilter.presentation.navigation.githubUsersGraph

@ExperimentalMaterial3Api
@Composable
fun GithubUserFilterContent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = GithubUsersScreenNavigation.route
    ) {
        githubUsersGraph(
            navigateToUserDetails = { navController.navigate(UserDetailsScreenDestination.route(it)) },
        )
        userDetailsGraph(
            navigateBack = navController::popBackStack
        )
    }
}
