package com.example.githubuserfilter.userdetails.domain.usercase

import com.example.githubuserfilter.core.result.ApiResult
import com.example.githubuserfilter.userdetails.domain.model.DetailUserInfo

interface GetUserDetails {
    suspend operator fun invoke(username: String): ApiResult<DetailUserInfo?>
}