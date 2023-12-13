package com.example.githubuserfilter.userdetails.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserfilter.core.result.onError
import com.example.githubuserfilter.core.result.onSuccess
import com.example.githubuserfilter.userdetails.domain.usercase.GetUserDetails
import com.example.githubuserfilter.userdetails.presentation.model.UserDetailsScreenUiState
import com.example.githubuserfilter.userdetails.presentation.navigation.UserDetailsScreenDestination.USERNAME
import com.example.githubuserfilter.userdetails.presentation.ui.UserDetailsScreenEvent.FetchUserDetails
import com.example.githubuserfilter.usersfilter.presentation.ui.GithubUsersScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserDetails: GetUserDetails
) : ViewModel() {
    val username = savedStateHandle.get<String>(USERNAME)

    private val _userDetailsState = MutableStateFlow(UserDetailsScreenState())
    val userDetailsState: StateFlow<UserDetailsScreenState> = _userDetailsState

    init {
        fetchUserDetails()
    }

    fun onEvent(event: UserDetailsScreenEvent) {
        when (event) {
            is FetchUserDetails -> { fetchUserDetails() }
        }
    }

    private fun fetchUserDetails() {
        _userDetailsState.update { currentState ->
            currentState.copy(
                uiState = UserDetailsScreenUiState.GetUserData
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            delay(GithubUsersScreenViewModel.FILTER_DEBOUNCE_MS)

            username?.let { username ->
                getUserDetails(username)
                    .onError {
                        _userDetailsState.update { currentState ->
                            currentState.copy(
                                uiState = UserDetailsScreenUiState.Error
                            )
                        }
                    }
                    .onSuccess { detailsUserInfo ->
                        _userDetailsState.update { currentState ->
                            currentState.copy(
                                userDetails = detailsUserInfo,
                                uiState = UserDetailsScreenUiState.Success
                            )
                        }
                    }
            }
        }
    }
}
