package com.example.githubuserfilter.usersfilter.presentation.model

sealed class GithubUsersScreenUIState {
    data object Idle : GithubUsersScreenUIState()
    data object StartFilteringUsers : GithubUsersScreenUIState()
    data object LoadMoreUsers : GithubUsersScreenUIState()
    data object Success : GithubUsersScreenUIState()
    data object Error : GithubUsersScreenUIState()
}
