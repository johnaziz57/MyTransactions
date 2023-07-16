package com.example.mytransactoins.data.di

import com.example.mytransactoins.data.repo.TransactionRepoImpl
import com.example.mytransactoins.data.repo.UserRepoImpl
import com.example.mytransactoins.domain.repo.TransactionRepo
import com.example.mytransactoins.domain.repo.UserRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {
    @Binds
    fun bindTransactionRepo(transactionRepoImpl: TransactionRepoImpl): TransactionRepo

    @Binds
    fun bindUserRepo(userRepoImpl: UserRepoImpl): UserRepo
}