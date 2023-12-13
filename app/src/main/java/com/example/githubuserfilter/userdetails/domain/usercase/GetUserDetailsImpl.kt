package com.example.githubuserfilter.userdetails.domain.usercase

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.userdetails.domain.model.DetailUserInfo
import com.example.githubuserfilter.userdetails.domain.repository.UserDetailsRepository
import javax.inject.Inject

class GetUserDetailsImpl @Inject constructor(
    private val userDetailsRepository: UserDetailsRepository
): GetUserDetails {
    override suspend fun invoke(username: String): ApiResult<DetailUserInfo?> {
        return userDetailsRepository.getUserDetailsByUsername(username)
    }
}
