package com.example.githubuserfilter.usersfilter.data.datasource

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.usersfilter.data.api.model.UserFilterResultDto

interface FilterUsersRemoteDatasource {
    suspend fun filterUsers(keyword: String, page: Int): ApiResult<UserFilterResultDto>
}
