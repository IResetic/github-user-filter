package com.example.githubuserfilter.githubusers.data.datasource

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.githubusers.data.api.FilterUsersApi
import com.example.githubuserfilter.githubusers.data.api.model.UserFilterResultDto
import com.example.githubuserfilter.githubusers.data.handler.handleApi
import javax.inject.Inject

class FilterUsersRemoteDatasourceImpl @Inject constructor(
    private val filterUsersApi: FilterUsersApi
) : FilterUsersRemoteDatasource {
    override suspend fun filterUsers(keyword: String, page: Int): ApiResult<UserFilterResultDto> {
        return handleApi { filterUsersApi.filterUsers(keyword = keyword, page = page) }
    }
}
