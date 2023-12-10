package com.example.githubuserfilter.githubusers.data.mappers

import com.example.githubuserfilter.githubusers.data.api.model.BasicUserInfoDto
import com.example.githubuserfilter.githubusers.domain.model.BasicUserInfo
import javax.inject.Inject

class BasicUserInfoDtoToBasicUserInfo @Inject constructor() {
    operator fun invoke(basicUserInfoDto: BasicUserInfoDto): BasicUserInfo? {
        return with(basicUserInfoDto) {
            if (userId == null) { return null }

            BasicUserInfo(
                userId = userId,
                username = username ?: "",
                avatarUrl = avatarUrl ?: "",
                type = type ?: "User",
                isSiteAdmin = isSiteAdmin ?: false,
                score = score ?: 0.0
            )
        }
    }
}
