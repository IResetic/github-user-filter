package com.example.githubuserfilter.userdetails.data.mapper

import com.example.githubuserfilter.userdetails.data.api.model.DetailsUserInfoDto
import com.example.githubuserfilter.userdetails.domain.model.DetailUserInfo
import javax.inject.Inject

class DetailsUserInfoDtoToDetailsUserInfo @Inject constructor() {
    operator fun invoke(detailsUserInfoDto: DetailsUserInfoDto): DetailUserInfo? {
        return with(detailsUserInfoDto) {
            if (id == null) return null

            DetailUserInfo(
                id = id,
                username = username ?: "",
                name = name ?: "",
                avatarUrl = avatarUrl ?: "",
                type = type ?: "User",
                email = email ?: "",
                bio = bio ?: "",
                followers = followers ?: 0,
                publicRepos = publicRepos ?: 0,
            )
        }
    }
}
