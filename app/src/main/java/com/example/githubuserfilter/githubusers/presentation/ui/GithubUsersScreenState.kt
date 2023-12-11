package com.example.githubuserfilter.githubusers.presentation.ui

import com.example.githubuserfilter.githubusers.domain.model.BasicUserInfo
import com.example.githubuserfilter.githubusers.presentation.model.GithubUsersScreenUIState

data class GithubUsersScreenState(
    val filterUsers : List<BasicUserInfo> = emptyList(),
    val errorMessage: String = "",
    val filterKeyword: String = "",
    val uiState: GithubUsersScreenUIState = GithubUsersScreenUIState.Idle
)
