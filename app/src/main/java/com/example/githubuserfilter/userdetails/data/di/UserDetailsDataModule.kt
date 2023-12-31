package com.example.githubuserfilter.userdetails.data.di

import com.example.githubuserfilter.userdetails.data.datasource.UserDetailsRemoteDataSource
import com.example.githubuserfilter.userdetails.data.datasource.UserDetailsRemoteDataSourceImpl
import com.example.githubuserfilter.userdetails.data.repository.UserDetailsRepositoryImpl
import com.example.githubuserfilter.userdetails.domain.repository.UserDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserDetailsDataModule {

    @Binds
    fun bindUserDetailsRepository(impl: UserDetailsRepositoryImpl): UserDetailsRepository

    @Binds
    fun bindsUserDetailsRemoteDatasource(impl: UserDetailsRemoteDataSourceImpl): UserDetailsRemoteDataSource
}
