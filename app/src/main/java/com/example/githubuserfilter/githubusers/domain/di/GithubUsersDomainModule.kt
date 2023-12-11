package com.example.githubuserfilter.githubusers.domain.di

import com.example.githubuserfilter.githubusers.domain.usecase.GetFilteredUsers
import com.example.githubuserfilter.githubusers.domain.usecase.GetFilteredUsersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GithubUsersDomainModule {
    @Binds
    fun bindsGetFilteredUsers(impl: GetFilteredUsersImpl) : GetFilteredUsers
}