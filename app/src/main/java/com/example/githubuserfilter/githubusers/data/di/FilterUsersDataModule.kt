package com.example.githubuserfilter.githubusers.data.di

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
    fun provideFilterUsersRepository(impl: FilterUsersRepositoryImpl): FilterUsersRepository
}
