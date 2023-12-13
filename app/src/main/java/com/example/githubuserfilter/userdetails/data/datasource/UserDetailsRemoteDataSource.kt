package com.example.githubuserfilter.userdetails.data.datasource

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.userdetails.data.api.model.DetailsUserInfoDto

interface UserDetailsRemoteDataSource {
    suspend fun getUserDetails(username: String): ApiResult<DetailsUserInfoDto>
}
