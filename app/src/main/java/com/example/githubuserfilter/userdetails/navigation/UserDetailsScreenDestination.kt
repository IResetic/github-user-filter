package com.example.githubuserfilter.userdetails.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.githubuserfilter.core.presentation.navigation.NavigationDestination
import com.example.githubuserfilter.userdetails.ui.UserDetailsScreen

object UserDetailsScreenDestination: NavigationDestination {
    const val USER_ID = "user_id"
    override val route: String = "user-details-screen-route?$USER_ID={$USER_ID}"
    override val destination: String = "user-details-screen-destination"

    fun route(userId: Int): String {
        return route.replace("{$USER_ID}", userId.toString())
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
                navArgument(UserDetailsScreenDestination.USER_ID) {type = NavType.IntType}
            )
        ) {
            UserDetailsScreen(
                navigateBack = navigateBack
            )
        }
    }
}

/*
        arguments = listOf(
            navArgument(LocationWeatherDestination.LOCATION_ID) { type = NavType.IntType }
        )
 */