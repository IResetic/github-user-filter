package com.example.githubuserfilter.githubusersfilter.data.di

import com.example.githubuserfilter.githubusersfilter.data.datasource.FilterUsersRemoteDatasource
import com.example.githubuserfilter.githubusersfilter.data.datasource.FilterUsersRemoteDatasourceImpl
import com.example.githubuserfilter.githubusersfilter.data.repository.FilterUsersRepositoryImpl
import com.example.githubuserfilter.githubusersfilter.domain.repository.FilterUsersRepository
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
