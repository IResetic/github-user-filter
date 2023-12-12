package com.example.githubuserfilter.githubusersfilter.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BasicUserInfoDto(
    @field:Json(name = "id") val userId: Int?,
    @field:Json(name = "login") val username: String?,
    @field:Json(name = "avatar_url") val avatarUrl: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "site_admin") val isSiteAdmin: Boolean?,
    @field:Json(name = "score") val score: Double?
)
