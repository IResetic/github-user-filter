package com.example.githubuserfilter.userdetails.data.di

import com.example.githubuserfilter.userdetails.data.api.UserDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserDetailsRemoteModule {

    @Singleton
    @Provides
    fun provideUserDetailsApi(retrofit: Retrofit): UserDetailsApi {
        return retrofit.create(UserDetailsApi::class.java)
    }
}
