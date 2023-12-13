package com.example.githubuserfilter.userdetails.data.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailsUserInfoDto(
    @field:Json(name = "id") val id: Long?,
    @field:Json(name = "login") val username: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "avatar_url") val avatarUrl: String?,
    @field:Json(name = "email") val email: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "bio") val bio: String?,
    @field:Json(name = "followers") val followers: Long?,
    @field:Json(name = "public_repos") val publicRepos: Long?
)
