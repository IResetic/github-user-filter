package com.example.githubuserfilter.githubusers.data.di

import com.example.githubuserfilter.githubusers.data.api.FilterUsersApi
import com.example.githubuserfilter.githubusers.data.datasource.FilterUsersRemoteDatasource
import com.example.githubuserfilter.githubusers.data.datasource.FilterUsersRemoteDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FilterUsersRemoteModule {

    @Singleton
    @Provides
    fun provideFilterUsersApi(retrofit: Retrofit): FilterUsersApi {
        return retrofit.create(FilterUsersApi::class.java)
    }

    @Binds
    fun providesFilterUsersRemoteDatasource(impl: FilterUsersRemoteDatasourceImpl): FilterUsersRemoteDatasource
}
