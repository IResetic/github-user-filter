package com.example.githubuserfilter.usersfilter.data.mappers

import com.example.githubuserfilter.usersfilter.data.api.model.BasicUserInfoDto
import com.example.githubuserfilter.usersfilter.domain.model.BasicUserInfo
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
