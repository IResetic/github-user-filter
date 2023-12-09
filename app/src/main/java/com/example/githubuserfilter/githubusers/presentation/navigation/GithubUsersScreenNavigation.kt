package com.example.githubuserfilter.githubusers.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.githubuserfilter.core.presentation.navigation.NavigationDestination
import com.example.githubuserfilter.githubusers.presentation.ui.GithubUsersScreen

object GithubUsersScreenNavigation : NavigationDestination {
    override val route: String = "github-users-screen-route"
    override val destination: String = "github-users-screen-destination"
}

fun NavGraphBuilder.githubUsersGraph() {
    navigation(
        route = GithubUsersScreenNavigation.route,
        startDestination = GithubUsersScreenNavigation.destination
    ) {
        composable(route = GithubUsersScreenNavigation.destination) {
            GithubUsersScreen()
        }
    }
}
