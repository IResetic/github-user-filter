package com.example.githubuserfilter.githubusers.data.di

import com.example.githubuserfilter.githubusers.data.datasource.FilterUsersRemoteDatasource
import com.example.githubuserfilter.githubusers.data.datasource.FilterUsersRemoteDatasourceImpl
import com.example.githubuserfilter.githubusers.data.repository.FilterUsersRepositoryImpl
import com.example.githubuserfilter.githubusers.domain.repository.FilterUsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FilterUsersDataModule {

    @Binds
    fun bindsFilterUsersRepository(impl: FilterUsersRepositoryImpl): FilterUsersRepository

    @Binds
    fun bindsFilterUsersRemoteDatasource(impl: FilterUsersRemoteDatasourceImpl): FilterUsersRemoteDatasource
}
