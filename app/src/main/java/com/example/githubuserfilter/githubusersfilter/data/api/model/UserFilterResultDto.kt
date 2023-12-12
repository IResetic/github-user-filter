package com.example.githubuserfilter.githubusersfilter.data.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserFilterResultDto(
    val totalCount: Int?,
    val isIncompleteResults: Boolean?,
    val items: List<BasicUserInfoDto>?
)
