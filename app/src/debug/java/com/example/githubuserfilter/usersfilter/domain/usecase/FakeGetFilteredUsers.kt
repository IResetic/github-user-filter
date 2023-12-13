package com.example.githubuserfilter.usersfilter.domain.usecase

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.core.result.ApiSuccess
import com.example.githubuserfilter.usersfilter.domain.model.BasicUserInfo
import com.example.githubuserfilter.usersfilter.domain.model.fakeBasicUserInfoOne
import com.example.githubuserfilter.usersfilter.domain.model.fakeBasicUserInfoThree
import com.example.githubuserfilter.usersfilter.domain.model.fakeBasicUserInfoTwo

class FakeGetFilteredUsers : GetFilteredUsers {
    var listOfUsers = listOf(fakeBasicUserInfoOne, fakeBasicUserInfoTwo, fakeBasicUserInfoThree)
    var result: ApiResult<List<BasicUserInfo?>> = ApiSuccess(data = listOfUsers)

    override suspend fun invoke(keyword: String, page: Int): ApiResult<List<BasicUserInfo?>> {
        return result
    }
}
