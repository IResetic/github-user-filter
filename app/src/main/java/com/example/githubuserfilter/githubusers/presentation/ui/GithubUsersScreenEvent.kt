package com.example.githubuserfilter.githubusers.presentation.ui

sealed class GithubUsersScreenEvent {
    data class FilterUsers(val queryString: String) : GithubUsersScreenEvent()
    // data class UpdateSearchKeyword(val keyword: String) : GithubUsersScreenEvent()
    data object LoadMoreUsers : GithubUsersScreenEvent()
}