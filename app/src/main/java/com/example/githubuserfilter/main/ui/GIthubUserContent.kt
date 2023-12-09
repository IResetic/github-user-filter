package com.example.githubuserfilter.main.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.githubuserfilter.githubusers.presentation.navigation.GithubUsersScreenNavigation
import com.example.githubuserfilter.githubusers.presentation.navigation.githubUsersGraph

@Composable
fun GithubUserFilterContent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = GithubUsersScreenNavigation.route
    ) {
        githubUsersGraph()
    }
}
