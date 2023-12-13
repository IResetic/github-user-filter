package com.example.githubuserfilter.userdetails.domain.repository

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.userdetails.domain.model.DetailUserInfo

interface UserDetailsRepository {
    suspend fun getUserDetailsByUsername(username: String): ApiResult<DetailUserInfo?>
}
