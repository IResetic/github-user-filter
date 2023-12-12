package com.example.githubuserfilter.githubusersfilter.presentation.ui

import com.example.githubuserfilter.githubusersfilter.domain.model.BasicUserInfo
import com.example.githubuserfilter.githubusersfilter.presentation.model.GithubUsersScreenUIState

data class GithubUsersScreenState(
    val filterUsers : List<BasicUserInfo> = emptyList(),
    val errorMessage: String = "",
    val filterKeyword: String = "",
    val uiState: GithubUsersScreenUIState = GithubUsersScreenUIState.Idle
)
