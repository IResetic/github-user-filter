package com.example.githubuserfilter.userdetails.data.datasource

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.githubusersfilter.data.handler.handleApi
import com.example.githubuserfilter.userdetails.data.api.UserDetailsApi
import com.example.githubuserfilter.userdetails.data.api.model.DetailsUserInfoDto
import javax.inject.Inject

class UserDetailsRemoteDatasourceImpl @Inject constructor(
    private val userDetailsApi: UserDetailsApi
) : UserDetailsRemoteDatasource {
    override suspend fun getUserDetails(username: String): ApiResult<DetailsUserInfoDto> {
        return handleApi { userDetailsApi.getUserDetails(username) }
    }
}