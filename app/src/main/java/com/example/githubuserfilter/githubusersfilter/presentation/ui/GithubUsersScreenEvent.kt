package com.example.githubuserfilter.githubusersfilter.presentation.ui

sealed class GithubUsersScreenEvent {
    data class FilterUsers(val queryString: String) : GithubUsersScreenEvent()
    data object LoadMoreUsers : GithubUsersScreenEvent()
}