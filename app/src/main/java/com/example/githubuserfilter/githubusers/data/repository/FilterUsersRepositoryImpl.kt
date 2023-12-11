package com.example.githubuserfilter.githubusers.data.repository

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.core.result.mapResult
import com.example.githubuserfilter.githubusers.data.datasource.FilterUsersRemoteDatasource
import com.example.githubuserfilter.githubusers.data.mappers.BasicUserInfoDtoToBasicUserInfo
import com.example.githubuserfilter.githubusers.domain.model.BasicUserInfo
import com.example.githubuserfilter.githubusers.domain.repository.FilterUsersRepository
import javax.inject.Inject

class FilterUsersRepositoryImpl @Inject constructor(
    private val filterUsersRemoteDatasource: FilterUsersRemoteDatasource,
    private val basicUserInfoDtoToBasicUserInfo: BasicUserInfoDtoToBasicUserInfo
) : FilterUsersRepository {

    override suspend fun filterUsersByKeyword(
        keyword: String,
        page: Int
    ): ApiResult<List<BasicUserInfo?>> {
        return filterUsersRemoteDatasource.filterUsers(
            keyword = keyword,
            page = page
        ).mapResult { userFilterResult ->
            userFilterResult.items?.map { basicUserInfoDto ->
                basicUserInfoDtoToBasicUserInfo(basicUserInfoDto)
            }
        }
    }
}
