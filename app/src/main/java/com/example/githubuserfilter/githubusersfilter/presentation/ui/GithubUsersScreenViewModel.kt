package com.example.githubuserfilter.githubusersfilter.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserfilter.core.result.ApiErrorType
import com.example.githubuserfilter.core.result.onError
import com.example.githubuserfilter.core.result.onSuccess
import com.example.githubuserfilter.githubusersfilter.domain.usecase.GetFilteredUsers
import com.example.githubuserfilter.githubusersfilter.presentation.model.GithubUsersScreenUIState
import com.example.githubuserfilter.githubusersfilter.presentation.ui.GithubUsersScreenEvent.FilterUsers
import com.example.githubuserfilter.githubusersfilter.presentation.ui.GithubUsersScreenEvent.LoadMoreUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUsersScreenViewModel @Inject constructor(
    private val getFilteredUsers: GetFilteredUsers
) : ViewModel() {

    private val _githubUsersState = MutableStateFlow(GithubUsersScreenState())
    val githubUsersState: StateFlow<GithubUsersScreenState> = _githubUsersState.asStateFlow()
    private var filterJob: Job? = null
    private var loadMoreJob: Job? = null
    private var offset: Int = 0

    fun onEvent(event: GithubUsersScreenEvent) {
        when(event) {
            is FilterUsers -> { setFilterKeyword(event.queryString) }
            is LoadMoreUsers -> { loadMore() }
        }
    }

    private fun setFilterKeyword(keyword: String) {
        _githubUsersState.update { currentState ->
            currentState.copy(
                filterKeyword = keyword,
                uiState = GithubUsersScreenUIState.StartFilteringUsers
            )
        }

        filterJob?.cancel()

        if(_githubUsersState.value.filterKeyword.isBlank()) {
            _githubUsersState.update { currentState ->
                currentState.copy(
                    uiState = GithubUsersScreenUIState.Idle,
                    filterUsers = emptyList()
                )
            }
            return
        }

        filterJob = viewModelScope.launch(Dispatchers.IO) {
            delay(FILTER_DEBOUNCE_MS)

            this@GithubUsersScreenViewModel.offset = 0

            filterUsers(keyword, 0)
        }
    }

    private suspend fun filterUsers(keyword: String, offset: Int) {
        delay(FILTER_DEBOUNCE_MS)

        if(_githubUsersState.value.filterKeyword.isBlank()) {
            _githubUsersState.update { currentState ->
                currentState.copy(
                    filterUsers = emptyList(),
                    uiState = GithubUsersScreenUIState.Idle
                )
            }
            return
        }

        getFilteredUsers.invoke(keyword, offset)
            .onError { error ->
                _githubUsersState.update {
                    it.copy(
                        errorMessage = setErrorMessage(error),
                        uiState = GithubUsersScreenUIState.Idle
                    )
                }
            }
            .onSuccess { users ->
                val filterUsers = users.filterNotNull().distinctBy { it.userId }

                val newValue = if(offset == 0) {
                    filterUsers
                } else {
                    _githubUsersState.value.filterUsers.toMutableList().apply {
                        addAll(filterUsers)
                    }
                }

                this@GithubUsersScreenViewModel.offset = offset + 1

                _githubUsersState.update { currentState ->
                    currentState.copy(
                        filterUsers = newValue,
                        uiState = GithubUsersScreenUIState.Idle
                    )
                }
            }
    }

    private fun loadMore() {
        if(loadMoreJob?.isActive == true) return

        _githubUsersState.update { currentState ->
            currentState.copy(uiState = GithubUsersScreenUIState.LoadMoreUsers)
        }

        loadMoreJob = viewModelScope.launch(Dispatchers.IO) {
            filterUsers(_githubUsersState.value.filterKeyword, offset)
        }
    }

    private fun setErrorMessage(apiError: ApiErrorType?): String {
        return when(apiError) {
            ApiErrorType.SERVER_ERROR -> { "Server Error" }
            ApiErrorType.NO_INTERNET_CONNECTION -> { "There is no internet connection" }
            ApiErrorType.CONNECTION_TIME_OUT -> { "Connection time out" }
            ApiErrorType.EXCEPTION -> { "The exception is thrown" }
            else -> { "Unknown error" }
        }
    }

    companion object {
        const val FILTER_DEBOUNCE_MS = 1000L
        const val PAGING_BUFFER = 20
    }
}
