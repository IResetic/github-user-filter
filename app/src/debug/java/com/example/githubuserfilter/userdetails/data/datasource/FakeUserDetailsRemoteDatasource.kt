package com.example.githubuserfilter.userdetails.data.datasource

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.core.result.ApiSuccess
import com.example.githubuserfilter.userdetails.data.api.model.DetailsUserInfoDto
import com.example.githubuserfilter.userdetails.data.api.model.fakeDetailsUserInfoDtoOne

class FakeUserDetailsRemoteDatasource : UserDetailsRemoteDataSource {
    val result: ApiResult<DetailsUserInfoDto> = ApiSuccess(data = fakeDetailsUserInfoDtoOne)

    override suspend fun getUserDetails(username: String): ApiResult<DetailsUserInfoDto> {
        return result
    }
}
