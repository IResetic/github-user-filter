package com.example.githubuserfilter.userdetails.data.repository

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.core.result.mapResult
import com.example.githubuserfilter.userdetails.data.datasource.UserDetailsRemoteDataSource
import com.example.githubuserfilter.userdetails.data.mapper.DetailsUserInfoDtoToDetailsUserInfo
import com.example.githubuserfilter.userdetails.domain.model.DetailUserInfo
import com.example.githubuserfilter.userdetails.domain.repository.UserDetailsRepository
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(
    private val userDetailsRemoteDatasource: UserDetailsRemoteDataSource,
    private val detailsUserInfoDtoToDetailsUserInfo: DetailsUserInfoDtoToDetailsUserInfo
) : UserDetailsRepository {
    override suspend fun getUserDetailsByUsername(username: String): ApiResult<DetailUserInfo?> {
        return userDetailsRemoteDatasource.getUserDetails(
            username = username
        ).mapResult { userDetails ->
            detailsUserInfoDtoToDetailsUserInfo(userDetails)
        }
    }
}
