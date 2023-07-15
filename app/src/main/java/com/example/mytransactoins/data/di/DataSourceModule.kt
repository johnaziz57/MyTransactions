package com.example.mytransactoins.data.di

import com.example.mytransactoins.data.datasource.FileUserDataSource
import com.example.mytransactoins.data.datasource.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindUserDataSource(fileUserDataSource: FileUserDataSource): UserDataSource
}
