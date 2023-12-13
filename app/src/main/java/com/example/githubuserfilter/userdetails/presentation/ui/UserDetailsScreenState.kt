package com.example.githubuserfilter.userdetails.presentation.ui

import com.example.githubuserfilter.userdetails.domain.model.DetailUserInfo
import com.example.githubuserfilter.userdetails.presentation.model.UserDetailsScreenUiState

data class UserDetailsScreenState(
    val userDetails: DetailUserInfo? = null,
    val uiState: UserDetailsScreenUiState = UserDetailsScreenUiState.Idle
)
