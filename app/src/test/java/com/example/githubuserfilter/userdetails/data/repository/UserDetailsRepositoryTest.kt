package com.example.githubuserfilter.userdetails.data.repository

import com.example.githubuserfilter.core.result.ApiSuccess
import com.example.githubuserfilter.userdetails.data.datasource.FakeUserDetailsRemoteDatasource
import com.example.githubuserfilter.userdetails.data.mapper.DetailsUserInfoDtoToDetailsUserInfo
import com.example.githubuserfilter.userdetails.domain.model.fakeDetailsUserInfoOne
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserDetailsRepositoryTest {
    private lateinit var userDetailsRemoteDatasource: FakeUserDetailsRemoteDatasource
    private lateinit var detailsUserInfoDtoToDetailsUserInfo: DetailsUserInfoDtoToDetailsUserInfo

    private lateinit var sut: UserDetailsRepositoryImpl

    @Before
    fun init() {
        userDetailsRemoteDatasource = FakeUserDetailsRemoteDatasource()
        detailsUserInfoDtoToDetailsUserInfo = DetailsUserInfoDtoToDetailsUserInfo()

        sut = UserDetailsRepositoryImpl(
            userDetailsRemoteDatasource = userDetailsRemoteDatasource,
            detailsUserInfoDtoToDetailsUserInfo = detailsUserInfoDtoToDetailsUserInfo
        )
    }

    @Test
    fun should_return_user_details_if_api_request_is_success() = runBlocking {
        // trigger action
        val actual = sut.getUserDetailsByUsername("User One")

        // check assertions
        val expected = ApiSuccess(data = fakeDetailsUserInfoOne)
        Assert.assertEquals(expected, actual)
    }
}
