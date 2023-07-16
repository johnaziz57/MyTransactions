package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.interactor.register.email_verification.EmailVerificationInteractorImpl
import com.example.mytransactoins.domain.model.NewResult
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EmailVerificationInteractorImplTest {

    private lateinit var emailVerificationInteractor: EmailVerificationInteractorImpl

    @Before
    fun setup() {
        emailVerificationInteractor = EmailVerificationInteractorImpl()
    }

    @Test
    fun `test too short email verification code`() {
        assert(emailVerificationInteractor.validateCode("1") is NewResult.Error)
        assert(emailVerificationInteractor.validateCode("12") is NewResult.Error)
        assert(emailVerificationInteractor.validateCode("123") is NewResult.Error)
    }

    @Test
    fun `test too long email verification code`() {
        assert(emailVerificationInteractor.validateCode("12345") is NewResult.Error)
        assert(emailVerificationInteractor.validateCode("123456") is NewResult.Error)
    }

    @Test
    fun `test email verification valid code`() {
        assert(emailVerificationInteractor.validateCode("1230") is NewResult.Success)
        assert(emailVerificationInteractor.validateCode("ABC0") is NewResult.Success)
    }
}