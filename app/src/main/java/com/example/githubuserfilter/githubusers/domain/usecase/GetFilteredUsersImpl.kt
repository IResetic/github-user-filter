package com.example.githubuserfilter.githubusers.domain.usecase

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.githubusers.domain.model.BasicUserInfo
import com.example.githubuserfilter.githubusers.domain.repository.FilterUsersRepository
import javax.inject.Inject

class GetFilteredUsersImpl @Inject constructor(
    private val filterUsersRepository: FilterUsersRepository
): GetFilteredUsers {
    override suspend fun invoke(keyword: String, page: Int): ApiResult<List<BasicUserInfo?>> {
        return filterUsersRepository.filterUsersByKeyword(keyword, page)
    }
}
