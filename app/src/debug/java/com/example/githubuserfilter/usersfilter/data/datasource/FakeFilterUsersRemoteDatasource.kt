package com.example.githubuserfilter.usersfilter.data.datasource

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.core.result.ApiSuccess
import com.example.githubuserfilter.usersfilter.data.api.model.UserFilterResultDto
import com.example.githubuserfilter.usersfilter.data.api.model.fakeUserFilterResultDtoOne

class FakeFilterUsersRemoteDatasource : FilterUsersRemoteDatasource {
    var result: ApiResult<UserFilterResultDto> = ApiSuccess(data = fakeUserFilterResultDtoOne)

    override suspend fun filterUsers(keyword: String, page: Int): ApiResult<UserFilterResultDto> {
        return result
    }
}
