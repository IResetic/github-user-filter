package com.example.githubuserfilter.githubusers.domain.model

data class BasicUserInfo(
    val userId: Int,
    val username: String,
    val avatarUrl: String,
    val type: String,
    val isSiteAdmin: Boolean,
    val score: Double
)
