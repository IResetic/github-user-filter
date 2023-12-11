package com.example.githubuserfilter.githubusers.presentation.ui

sealed class GithubUsersScreenEvent {
    data class FilterUsers(val queryString: String) : GithubUsersScreenEvent()
    data object LoadMoreUsers : GithubUsersScreenEvent()
}