package com.example.githubuserfilter.userdetails.domain.model

data class DetailUserInfo(
    val id: Int,
    val username: String,
    val name: String,
    val avatarUrl: String,
    val type: String,
    val email: String,
    val bio: String,
    val followers: Int,
    val publicRepos: Int
)
