package com.example.mytransactoins.domain.di

import com.example.mytransactoins.domain.interactor.common.ValidateEmailInteractor
import com.example.mytransactoins.domain.interactor.common.ValidateEmailInteractorImpl
import com.example.mytransactoins.domain.interactor.entry.EntryInteractor
import com.example.mytransactoins.domain.interactor.entry.EntryInteractorImpl
import com.example.mytransactoins.domain.interactor.login.LoginInteractor
import com.example.mytransactoins.domain.interactor.login.LoginInteractorImpl
import com.example.mytransactoins.domain.interactor.register.RegistrationInteractor
import com.example.mytransactoins.domain.interactor.register.RegistrationInteractorImpl
import com.example.mytransactoins.domain.interactor.register.ValidateRegisterPasswordInteractor
import com.example.mytransactoins.domain.interactor.register.ValidateRegisterPasswordInteractorImpl
import com.example.mytransactoins.domain.interactor.register.email_verification.EmailVerificationInteractor
import com.example.mytransactoins.domain.interactor.register.email_verification.EmailVerificationInteractorImpl
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
    fun bindEntryInteractor(entryInteractorImpl: EntryInteractorImpl): EntryInteractor

    @Binds
    fun bindLoginInteractor(loginInteractorImpl: LoginInteractorImpl): LoginInteractor

    @Binds
    fun bindEmailInteractor(emailInteractorImpl: ValidateEmailInteractorImpl): ValidateEmailInteractor

    @Binds
    fun bindPasswordInteractor(validatePasswordInteractorImpl: ValidateRegisterPasswordInteractorImpl): ValidateRegisterPasswordInteractor

    @Binds
    fun bindRegistrationInteractor(registrationInteractorImpl: RegistrationInteractorImpl): RegistrationInteractor


    @Binds
    fun bindVerificationInteractor(verificationCodeInteractorImpl: EmailVerificationInteractorImpl): EmailVerificationInteractor

    @Binds
    fun bindTransactionInteractor(transactionInteractorImpl: TransactionInteractorImpl): TransactionInteractor

}