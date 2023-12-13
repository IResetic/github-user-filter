package com.example.githubuserfilter.githubusersfilter.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.githubuserfilter.core.presentation.navigation.NavigationDestination
import com.example.githubuserfilter.githubusersfilter.presentation.ui.GithubUsersScreen

object GithubUsersScreenNavigation : NavigationDestination {
    override val route: String = "github-users-screen-route"
    override val destination: String = "github-users-screen-destination"
}

@ExperimentalMaterial3Api
fun NavGraphBuilder.githubUsersGraph(
    navigateToUserDetails: (String) -> Unit,
) {
    navigation(
        route = GithubUsersScreenNavigation.route,
        startDestination = GithubUsersScreenNavigation.destination
    ) {
        composable(route = GithubUsersScreenNavigation.destination) {
            GithubUsersScreen(
                navigateToUserDetails = navigateToUserDetails,
            )
        }
    }
}
