package com.example.githubuserfilter.usersfilter.presentation.ui

import com.example.githubuserfilter.core.result.ApiError
import com.example.githubuserfilter.core.result.ApiErrorType
import com.example.githubuserfilter.usersfilter.domain.usecase.FakeGetFilteredUsers
import com.example.githubuserfilter.usersfilter.presentation.model.GithubUsersScreenUIState
import com.example.githubuserfilter.usersfilter.presentation.ui.GithubUsersScreenEvent.FilterUsers
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class GithubUsersScreenViewModelTest {
    private lateinit var getFilteredUsers: FakeGetFilteredUsers

    private lateinit var sut: GithubUsersScreenViewModel

    @Before
    fun init() {
        getFilteredUsers = FakeGetFilteredUsers()

        sut = GithubUsersScreenViewModel(
            getFilteredUsers = getFilteredUsers
        )
    }

    @Test
    fun should_return_list_of_filter_users() = runBlocking {
        // trigger action
        sut.onEvent(FilterUsers("User"))

        // check assertions
        val actual = sut.githubUsersState.take(2).last()
        val expected = getFilteredUsers.listOfUsers
        val expectedState = GithubUsersScreenUIState.Success

        assertEquals(expectedState, actual.uiState)
        assertEquals(expected, actual.filterUsers)
    }

    @Test
    fun should_return_api_error_if_filtering_users_fails() = runBlocking {
        // define test data
        getFilteredUsers.result = ApiError(ApiErrorType.SERVER_ERROR)

        // trigger action
        sut.onEvent(FilterUsers("User"))

        // check assertions
        val actual = sut.githubUsersState.take(2).last()
        assertNotNull(actual.errorType)
    }
}
