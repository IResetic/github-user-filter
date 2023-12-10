package com.example.githubuserfilter.githubusers.domain.repository

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.githubusers.domain.model.BasicUserInfo

interface FilterUsersRepository {
    suspend fun filterUsersByKeyword(keyword: String, page: Int): ApiResult<List<BasicUserInfo?>>
}
