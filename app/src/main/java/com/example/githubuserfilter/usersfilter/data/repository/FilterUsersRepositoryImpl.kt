package com.example.githubuserfilter.usersfilter.data.repository

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.core.result.mapResult
import com.example.githubuserfilter.usersfilter.data.datasource.FilterUsersRemoteDatasource
import com.example.githubuserfilter.usersfilter.data.mappers.BasicUserInfoDtoToBasicUserInfo
import com.example.githubuserfilter.usersfilter.domain.model.BasicUserInfo
import com.example.githubuserfilter.usersfilter.domain.repository.FilterUsersRepository
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
