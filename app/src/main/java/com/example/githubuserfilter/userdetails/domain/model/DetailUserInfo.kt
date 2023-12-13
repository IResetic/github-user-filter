package com.example.githubuserfilter.userdetails.domain.model

data class DetailUserInfo(
    val id: Long,
    val username: String,
    val name: String,
    val avatarUrl: String,
    val type: String,
    val email: String,
    val bio: String,
    val followers: Long,
    val publicRepos: Long
)
