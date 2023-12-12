package com.example.githubuserfilter.githubusersfilter.data.datasource

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.githubusersfilter.data.api.model.UserFilterResultDto

interface FilterUsersRemoteDatasource {
    suspend fun filterUsers(keyword: String, page: Int): ApiResult<UserFilterResultDto>
}
