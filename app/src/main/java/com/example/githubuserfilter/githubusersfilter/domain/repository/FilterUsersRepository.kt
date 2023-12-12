package com.example.githubuserfilter.githubusersfilter.domain.repository

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.githubusersfilter.domain.model.BasicUserInfo

interface FilterUsersRepository {
    suspend fun filterUsersByKeyword(keyword: String, page: Int): ApiResult<List<BasicUserInfo?>>
}
