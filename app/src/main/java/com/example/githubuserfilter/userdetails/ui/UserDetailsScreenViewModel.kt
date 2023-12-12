package com.example.githubuserfilter.userdetails.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.githubuserfilter.userdetails.navigation.UserDetailsScreenDestination.USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    val userId = savedStateHandle.get<Int>(USER_ID)
}