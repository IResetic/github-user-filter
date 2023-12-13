package com.example.githubuserfilter.usersfilter.data.di

import com.example.githubuserfilter.usersfilter.data.api.FilterUsersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FilterUsersRemoteModule {

    @Singleton
    @Provides
    fun provideFilterUsersApi(retrofit: Retrofit): FilterUsersApi {
        return retrofit.create(FilterUsersApi::class.java)
    }
}
