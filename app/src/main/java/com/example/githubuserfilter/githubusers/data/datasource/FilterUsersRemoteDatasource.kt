package com.example.githubuserfilter.githubusers.data.datasource

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.githubusers.data.api.model.UserFilterResultDto

interface FilterUsersRemoteDatasource {
    suspend fun filterUsers(keyword: String, page: Int): ApiResult<UserFilterResultDto>
}
