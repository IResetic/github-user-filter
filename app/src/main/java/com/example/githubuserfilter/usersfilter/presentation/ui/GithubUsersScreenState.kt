package com.example.githubuserfilter.usersfilter.presentation.ui

import com.example.githubuserfilter.core.result.ApiErrorType
import com.example.githubuserfilter.usersfilter.domain.model.BasicUserInfo
import com.example.githubuserfilter.usersfilter.presentation.model.GithubUsersScreenUIState
import javax.annotation.concurrent.Immutable

@Immutable
data class GithubUsersScreenState(
    val filterUsers: List<BasicUserInfo> = emptyList(),
    val filterKeyword: String = "",
    val errorType: ApiErrorType? = null,
    val uiState: GithubUsersScreenUIState = GithubUsersScreenUIState.Idle
)
