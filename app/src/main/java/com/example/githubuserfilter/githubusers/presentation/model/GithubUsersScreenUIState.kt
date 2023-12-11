package com.example.githubuserfilter.githubusers.presentation.model

sealed class GithubUsersScreenUIState {
    data object Idle : GithubUsersScreenUIState()
    data object StartFilteringUsers : GithubUsersScreenUIState()
    data object LoadMoreUsers : GithubUsersScreenUIState()
}