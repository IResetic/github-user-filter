package com.example.githubuserfilter.userdetails.presentation.model

sealed class UserDetailsScreenUiState {
    data object Idle : UserDetailsScreenUiState()
    data object GetUserData : UserDetailsScreenUiState()
    data object Success: UserDetailsScreenUiState()
    data object Error: UserDetailsScreenUiState()
}
