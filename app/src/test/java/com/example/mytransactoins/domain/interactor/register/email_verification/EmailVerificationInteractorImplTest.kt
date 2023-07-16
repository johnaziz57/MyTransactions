package com.example.mytransactoins.domain.interactor.register.email_verification

import com.example.mytransactoins.domain.model.Result
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
        assert(emailVerificationInteractor.validateCode("1") is Result.Error)
        assert(emailVerificationInteractor.validateCode("12") is Result.Error)
        assert(emailVerificationInteractor.validateCode("123") is Result.Error)
    }

    @Test
    fun `test too long email verification code`() {
        assert(emailVerificationInteractor.validateCode("12345") is Result.Error)
        assert(emailVerificationInteractor.validateCode("123456") is Result.Error)
    }

    @Test
    fun `test email verification valid code`() {
        assert(emailVerificationInteractor.validateCode("1230") is Result.Success)
        assert(emailVerificationInteractor.validateCode("ABC0") is Result.Success)
    }
}