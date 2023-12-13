package com.example.githubuserfilter.usersfilter.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserfilter.core.result.onError
import com.example.githubuserfilter.core.result.onSuccess
import com.example.githubuserfilter.usersfilter.domain.model.BasicUserInfo
import com.example.githubuserfilter.usersfilter.domain.usecase.GetFilteredUsers
import com.example.githubuserfilter.usersfilter.presentation.model.GithubUsersScreenUIState
import com.example.githubuserfilter.usersfilter.presentation.ui.GithubUsersScreenEvent.ClearErrorType
import com.example.githubuserfilter.usersfilter.presentation.ui.GithubUsersScreenEvent.FilterUsers
import com.example.githubuserfilter.usersfilter.presentation.ui.GithubUsersScreenEvent.LoadMoreUsers
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

    private var _githubUsersState = MutableStateFlow(GithubUsersScreenState())
    val githubUsersState: StateFlow<GithubUsersScreenState> = _githubUsersState.asStateFlow()
    private var filterJob: Job? = null
    private var loadMoreJob: Job? = null
    private var offset: Int = 0

    fun onEvent(event: GithubUsersScreenEvent) {
        when (event) {
            is FilterUsers -> { startFilteringUsers(event.queryString) }
            is LoadMoreUsers -> { loadMore() }
            is ClearErrorType -> { clearErrorType() }
        }
    }

    private fun startFilteringUsers(keyword: String) {
        _githubUsersState.update { currentState ->
            currentState.copy(filterKeyword = keyword,)
        }

        if(keyword.length < 2) return

        _githubUsersState.update { currentState ->
            currentState.copy(uiState = GithubUsersScreenUIState.StartFilteringUsers)
        }

        filterJob?.cancel()

        if (_githubUsersState.value.filterKeyword.isBlank()) {
            clearFilteredList()
            return
        }

        filterJob = viewModelScope.launch(Dispatchers.IO) {
            this@GithubUsersScreenViewModel.offset = 0

            filterUsers(keyword, 0)
        }
    }

    private suspend fun filterUsers(keyword: String, offset: Int) {
        delay(FILTER_DEBOUNCE_MS)

        if (_githubUsersState.value.filterKeyword.isBlank()) {
            clearFilteredList()
            return
        }

        getFilteredUsers.invoke(keyword, offset)
            .onError { error ->
                _githubUsersState.update {
                    it.copy(
                        errorType = error,
                        uiState = GithubUsersScreenUIState.Error,
                    )
                }
            }
            .onSuccess { users ->
                this@GithubUsersScreenViewModel.offset = offset + 1

                _githubUsersState.update { currentState ->
                    currentState.copy(
                        filterUsers = updateFilteredUsersList(users, offset),
                        errorType = null,
                        uiState = GithubUsersScreenUIState.Success
                    )
                }
            }
    }

    private fun updateFilteredUsersList(newUsers: List<BasicUserInfo?>, offset: Int) :  List<BasicUserInfo> {
        val filterUsers = newUsers.filterNotNull().distinctBy { it.userId }

        return if (offset == 0) {
            filterUsers
        } else {
            _githubUsersState.value.filterUsers.toMutableList().apply {
                addAll(filterUsers)
            }
        }
    }

    private fun loadMore() {
        if (loadMoreJob?.isActive == true) return

        _githubUsersState.update { currentState ->
            currentState.copy(
                uiState = GithubUsersScreenUIState.LoadMoreUsers,
            )
        }

        loadMoreJob = viewModelScope.launch(Dispatchers.IO) {
            filterUsers(_githubUsersState.value.filterKeyword, offset)
        }
    }

    private fun clearErrorType() {
        _githubUsersState.update { currentState ->
            currentState.copy(
                errorType = null,
                uiState = GithubUsersScreenUIState.Idle
            )
        }
    }

    private fun clearFilteredList() {
        _githubUsersState.update { currentState ->
            currentState.copy(
                uiState = GithubUsersScreenUIState.Idle,
                filterUsers = emptyList()
            )
        }
    }

    companion object {
        const val FILTER_DEBOUNCE_MS = 1000L
        const val PAGING_BUFFER = 20
    }
}
