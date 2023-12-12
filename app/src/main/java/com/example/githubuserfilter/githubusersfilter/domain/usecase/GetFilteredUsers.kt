package com.example.githubuserfilter.githubusersfilter.domain.usecase

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.githubusersfilter.domain.model.BasicUserInfo

interface GetFilteredUsers {
    suspend operator fun invoke(keyword: String, page: Int): ApiResult<List<BasicUserInfo?>>
}
