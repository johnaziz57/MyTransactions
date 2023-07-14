package com.example.mytransactoins.domain.di

import com.example.mytransactoins.domain.interactor.login.LoginInteractor
import com.example.mytransactoins.domain.interactor.login.LoginInteractorImpl
import com.example.mytransactoins.domain.interactor.register.EmailInteractor
import com.example.mytransactoins.domain.interactor.register.EmailInteractorImpl
import com.example.mytransactoins.domain.interactor.register.PasswordInteractor
import com.example.mytransactoins.domain.interactor.register.PasswordInteractorImpl
import com.example.mytransactoins.domain.interactor.register.RegistrationInteractor
import com.example.mytransactoins.domain.interactor.register.RegistrationInteractorImpl
import com.example.mytransactoins.domain.interactor.register.VerificationCodeInteractor
import com.example.mytransactoins.domain.interactor.register.VerificationCodeInteractorImpl
import com.example.mytransactoins.domain.interactor.transaction.TransactionInteractor
import com.example.mytransactoins.domain.interactor.transaction.TransactionInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InteractorModule {
    @Binds
    fun bindLoginInteractor(loginInteractorImpl: LoginInteractorImpl): LoginInteractor


    @Binds
    fun bindEmailInteractor(emailInteractorImpl: EmailInteractorImpl): EmailInteractor

    @Binds
    fun bindPasswordInteractor(passwordInteractorImpl: PasswordInteractorImpl): PasswordInteractor

    @Binds
    fun bindRegistrationInteractor(registrationInteractorImpl: RegistrationInteractorImpl): RegistrationInteractor


    @Binds
    fun bindVerificationInteractor(verificationCodeInteractorImpl: VerificationCodeInteractorImpl): VerificationCodeInteractor

    @Binds
    fun bindTransactionInteractor(transactionInteractorImpl: TransactionInteractorImpl): TransactionInteractor
}