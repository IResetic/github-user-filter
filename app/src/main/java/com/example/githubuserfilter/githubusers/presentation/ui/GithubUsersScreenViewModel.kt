package com.example.githubuserfilter.githubusers.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserfilter.core.data.api.PAGE_SIZE
import com.example.githubuserfilter.core.result.ApiError
import com.example.githubuserfilter.core.result.ApiErrorType
import com.example.githubuserfilter.core.result.onError
import com.example.githubuserfilter.core.result.onSuccess
import com.example.githubuserfilter.githubusers.domain.model.BasicUserInfo
import com.example.githubuserfilter.githubusers.domain.usecase.GetFilteredUsers
import com.example.githubuserfilter.githubusers.presentation.model.GithubUsersScreenUIState
import com.example.githubuserfilter.githubusers.presentation.ui.GithubUsersScreenEvent.*
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
    // private var currentPage = 0
    private var filterJob: Job? = null
    private var isNewKeyword = false
    private var loadMoreJob: Job? = null

    private var query: String? = null
    private var offset: Int = 0

    // private val _filterKeyword = MutableStateFlow("")
    //val filterKeyword = _filterKeyword.asStateFlow()

    fun onEvent(event: GithubUsersScreenEvent) {
        when(event) {
            // is FilterUsers -> { filterUsersByKeyword(event.queryString) }
            is FilterUsers -> { setFilterKeyword(event.queryString) }
            // is UpdateSearchKeyword -> { updateFilterKeyword(event.keyword) }
            //is LoadMoreUsers -> { loadMoreData() }
            is LoadMoreUsers -> { loadMore() }
        }
    }

    /*
    private fun updateFilterKeyword(keyword: String) {
        _filterKeyword.tryEmit(keyword)
        isNewKeyword = true
    }

     */

    private fun setFilterKeyword(keyword: String) {
        // _filterKeyword.tryEmit(keyword)

        _githubUsersState.update { currentState ->
            currentState.copy(
                filterKeyword2 = keyword,
                uiState = GithubUsersScreenUIState.StartFilteringUsers
            )
        }

        filterJob?.cancel()

        // if(_filterKeyword.value.isBlank()) {
        if(_githubUsersState.value.filterKeyword2.isBlank()) {
            _githubUsersState.update { currentState ->
                currentState.copy(
                    uiState = GithubUsersScreenUIState.Idle
                )
            }
            return
        }

        filterJob = viewModelScope.launch(Dispatchers.IO) {
            delay(FILTER_DEBOUNCE_MS)

            // this@GithubUsersScreenViewModel.query = keyword
            this@GithubUsersScreenViewModel.offset = 0

            filterUsers(keyword, 0)
        }
    }

    private suspend fun filterUsers(keyword: String, offset: Int) {
        //_githubUsersState.update { currentState -> currentState.copy(isNewFiltering = true) }

        delay(FILTER_DEBOUNCE_MS)

        // if(_filterKeyword.value.isBlank()) {
        if(_githubUsersState.value.filterKeyword2.isBlank()) {
            // Emit empty state here
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
            //val queryValue = query ?: return@launch
            //filterUsers(_filterKeyword.value, offset)
            filterUsers(_githubUsersState.value.filterKeyword2, offset)
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
    }
}

/*

    private fun filterUsersByKeyword() {
        filterJob?.cancel()

        filterJob = viewModelScope.launch(Dispatchers.IO) {
            _githubUsersState.update { currentState -> currentState.copy(isNewFiltering = true) }

            delay(FILTER_DEBOUNCE_MS)

            if(filterKeyword.value.isBlank()) {
                _githubUsersState.update { currentState ->
                    currentState.copy(
                        filterUsers = emptyList(),
                        isNewFiltering = false
                    )
                }
                return@launch
            }

            getFilteredUsers.invoke(filterKeyword.value, currentPage)
                .onError { error ->
                    Log.d("TEST_FILTER", "ERROR $error")
                    _githubUsersState.update {
                        it.copy(
                            errorMessage = "Error message",
                            isNewFiltering = false
                        )
                    }
                }
                .onSuccess { users ->
                    Log.d("TEST_FILTER", "SUCCESS")
                    val filterUsers = users.filterNotNull()
                    handleOnSuccessApiResponse(filterUsers)
                }
        }
    }

        private fun handleOnSuccessApiResponse(users: List<BasicUserInfo>) {
        val currentUsers = if (!isNewKeyword) {
            Log.d("TEST_HANDLER","APPEND USERS")
            _githubUsersState.value.filterUsers + users
        } else {
            Log.d("TEST_HANDLER","NEW KEYWORD NEW USERS")
            isNewKeyword = false
            users
        }

        _githubUsersState.update { currentState ->
            currentState.copy(
                filterUsers = currentUsers.toList(),
                isNewFiltering = false
            )
        }
    }



    private fun filterUsersByKeyword(keyword: String) {
        filterJob?.cancel()

        _filterKeyword.tryEmit(keyword)

        filterJob = viewModelScope.launch(Dispatchers.IO) {
            _githubUsersState.update { currentState -> currentState.copy(isNewFiltering = true) }

            delay(FILTER_DEBOUNCE_MS)

            if(filterKeyword.value.isBlank()) {
                _githubUsersState.update { currentState ->
                    currentState.copy(
                        filterUsers = emptyList(),
                        isNewFiltering = false
                    )
                }
                return@launch
            }

            getFilteredUsers.invoke(filterKeyword.value, currentPage)
                .onError { _ ->
                    _githubUsersState.update {
                        it.copy(
                            errorMessage = "Error message",
                            isNewFiltering = false
                        )
                    }
                }
                .onSuccess { users ->
                    val filterUsers = users.filterNotNull()

                    _githubUsersState.update { currentState ->
                        currentState.copy(
                            filterUsers = filterUsers,
                            isNewFiltering = false
                        )
                    }
                }
        }
    }

    private fun loadMoreData() {
        _githubUsersState.update { currentState ->
            currentState.copy(errorMessage = "")
        }

        if(loadMoreJob?.isActive == true) return

        loadMoreJob = viewModelScope.launch(Dispatchers.IO) {
            _githubUsersState.update { currentState -> currentState.copy(isLoadingMore = true) }

            delay(FILTER_DEBOUNCE_MS)

            getFilteredUsers.invoke(filterKeyword.value, currentPage)
                .onError { error ->
                    _githubUsersState.update {
                        it.copy(
                            errorMessage = setErrorMessage(error),
                            isLoadingMore = false
                        )
                    }
                }
                .onSuccess { users ->
                    val filterUsers = users.filterNotNull()
                    currentPage++

                    _githubUsersState.update { currentState ->
                        currentState.copy(
                            filterUsers = _githubUsersState.value.filterUsers + filterUsers,
                            isLoadingMore = false
                        )
                    }
                }
        }
    }

 */