package com.example.githubuserfilter.userdetails.domain.di

import com.example.githubuserfilter.userdetails.domain.usercase.GetUserDetails
import com.example.githubuserfilter.userdetails.domain.usercase.GetUserDetailsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserDetailsDomainModule {
    @Binds
    fun bindGetUserDetails(impl: GetUserDetailsImpl): GetUserDetails
}
