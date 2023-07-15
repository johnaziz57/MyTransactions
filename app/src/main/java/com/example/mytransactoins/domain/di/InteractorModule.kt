package com.example.mytransactoins.domain.di

import com.example.mytransactoins.data.repo.CryptoManagerImpl
import com.example.mytransactoins.domain.interactor.entry.EntryInteractor
import com.example.mytransactoins.domain.interactor.entry.EntryInteractorImpl
import com.example.mytransactoins.domain.interactor.register.EmailInteractor
import com.example.mytransactoins.domain.interactor.register.EmailInteractorImpl
import com.example.mytransactoins.domain.interactor.register.EmailVerificationInteractor
import com.example.mytransactoins.domain.interactor.register.EmailVerificationInteractorImpl
import com.example.mytransactoins.domain.interactor.register.PasswordInteractor
import com.example.mytransactoins.domain.interactor.register.PasswordInteractorImpl
import com.example.mytransactoins.domain.interactor.register.RegistrationInteractor
import com.example.mytransactoins.domain.interactor.register.RegistrationInteractorImpl
import com.example.mytransactoins.domain.interactor.transaction.TransactionInteractor
import com.example.mytransactoins.domain.interactor.transaction.TransactionInteractorImpl
import com.example.mytransactoins.domain.repo.CryptoManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InteractorModule {

    @Binds
    fun bindEntryInteractor(entryInteractorImpl: EntryInteractorImpl): EntryInteractor

    @Binds
    fun bindEmailInteractor(emailInteractorImpl: EmailInteractorImpl): EmailInteractor

    @Binds
    fun bindPasswordInteractor(passwordInteractorImpl: PasswordInteractorImpl): PasswordInteractor

    @Binds
    fun bindRegistrationInteractor(registrationInteractorImpl: RegistrationInteractorImpl): RegistrationInteractor


    @Binds
    fun bindVerificationInteractor(verificationCodeInteractorImpl: EmailVerificationInteractorImpl): EmailVerificationInteractor

    @Binds
    fun bindTransactionInteractor(transactionInteractorImpl: TransactionInteractorImpl): TransactionInteractor

    @Binds
    fun bindCryptoManager(cryptoManagerImpl: CryptoManagerImpl): CryptoManager
}