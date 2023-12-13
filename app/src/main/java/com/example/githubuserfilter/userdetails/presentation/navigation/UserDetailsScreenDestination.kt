package com.example.githubuserfilter.userdetails.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.githubuserfilter.core.presentation.navigation.NavigationDestination
import com.example.githubuserfilter.userdetails.presentation.ui.UserDetailsScreen

object UserDetailsScreenDestination: NavigationDestination {
    const val USERNAME = "user_id"
    override val route: String = "user-details-screen-route?$USERNAME={$USERNAME}"
    override val destination: String = "user-details-screen-destination"

    fun route(username: String): String {
        return route.replace("{$USERNAME}", username)
    }
}

fun NavGraphBuilder.userDetailsGraph(
    navigateBack: () -> Unit
) {
    navigation(
        route = UserDetailsScreenDestination.destination,
        startDestination = UserDetailsScreenDestination.route
    ) {
        composable(
            route = UserDetailsScreenDestination.route,
            arguments = listOf(
                navArgument(UserDetailsScreenDestination.USERNAME) { type = NavType.StringType }
            )
        ) {
            UserDetailsScreen(
                navigateBack = navigateBack
            )
        }
    }
}
