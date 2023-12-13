package com.example.githubuserfilter.userdetails.presentation.ui

sealed class UserDetailsScreenEvent {
    data object FetchUserDetails : UserDetailsScreenEvent()
}
