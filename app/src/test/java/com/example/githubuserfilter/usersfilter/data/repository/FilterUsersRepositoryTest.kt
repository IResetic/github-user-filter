package com.example.githubuserfilter.usersfilter.data.repository

import com.example.githubuserfilter.core.result.ApiError
import com.example.githubuserfilter.core.result.ApiErrorType
import com.example.githubuserfilter.core.result.ApiSuccess
import com.example.githubuserfilter.usersfilter.data.api.model.UserFilterResultDto
import com.example.githubuserfilter.usersfilter.data.api.model.fakeBasicUserInfoDtoOne
import com.example.githubuserfilter.usersfilter.data.api.model.fakeBasicUserInfoDtoTwo
import com.example.githubuserfilter.usersfilter.data.api.model.fakeUserFilterResultDtoOne
import com.example.githubuserfilter.usersfilter.data.datasource.FakeFilterUsersRemoteDatasource
import com.example.githubuserfilter.usersfilter.data.mappers.BasicUserInfoDtoToBasicUserInfo
import com.example.githubuserfilter.usersfilter.domain.model.fakeBasicUserInfoOne
import com.example.githubuserfilter.usersfilter.domain.model.fakeBasicUserInfoTwo
import com.example.githubuserfilter.usersfilter.domain.repository.FilterUsersRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FilterUsersRepositoryTest {
    private lateinit var filterUsersRemoteDatasource: FakeFilterUsersRemoteDatasource
    private lateinit var basicUserInfoDtoToBasicUserInfo: BasicUserInfoDtoToBasicUserInfo

    private lateinit var sut: FilterUsersRepository

    @Before
    fun init() {
        filterUsersRemoteDatasource = FakeFilterUsersRemoteDatasource()
        basicUserInfoDtoToBasicUserInfo = BasicUserInfoDtoToBasicUserInfo()

        sut = FilterUsersRepositoryImpl(
            filterUsersRemoteDatasource = filterUsersRemoteDatasource,
            basicUserInfoDtoToBasicUserInfo = basicUserInfoDtoToBasicUserInfo
        )
    }

    @Test
    fun should_return_list_of_filter_users_when_api_request_is_success() = runBlocking {
        // define test data
        val listOfUsers = listOf(fakeBasicUserInfoDtoOne, fakeBasicUserInfoDtoTwo)
        val filterUsersResult = fakeUserFilterResultDtoOne.copy(
            items = listOfUsers
        )
        filterUsersRemoteDatasource.result = ApiSuccess(data = filterUsersResult)

        // trigger action
        val actual = sut.filterUsersByKeyword("User", 1)

        // check assertions
        val expected = ApiSuccess(data = listOf(fakeBasicUserInfoOne, fakeBasicUserInfoTwo))
        assertEquals(expected, actual)
    }

    @Test
    fun should_return_result_error_if_api_request_fails() = runBlocking {
        // define test data
        filterUsersRemoteDatasource.result = ApiError(ApiErrorType.SERVER_ERROR)

        // trigger action
        val actual = sut.filterUsersByKeyword("User", 1)

        // check assertions
        val expected = ApiError<UserFilterResultDto>(ApiErrorType.SERVER_ERROR)
        assertEquals(expected, actual)
    }
}
