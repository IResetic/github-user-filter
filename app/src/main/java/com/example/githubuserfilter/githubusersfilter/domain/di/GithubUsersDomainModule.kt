package com.example.githubuserfilter.githubusersfilter.domain.di

import com.example.githubuserfilter.githubusersfilter.domain.usecase.GetFilteredUsers
import com.example.githubuserfilter.githubusersfilter.domain.usecase.GetFilteredUsersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface GithubUsersDomainModule {
    @Binds
    fun bindsGetFilteredUsers(impl: GetFilteredUsersImpl): GetFilteredUsers
}
