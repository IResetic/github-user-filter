package com.example.githubuserfilter.usersfilter.domain.repository

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.usersfilter.domain.model.BasicUserInfo

interface FilterUsersRepository {
    suspend fun filterUsersByKeyword(keyword: String, page: Int): ApiResult<List<BasicUserInfo?>>
}
