package com.example.mytransactoins.domain.interactor.register

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
        assert(emailVerificationInteractor.validateCode("1").isSuccessful.not())
        assert(emailVerificationInteractor.validateCode("12").isSuccessful.not())
        assert(emailVerificationInteractor.validateCode("123").isSuccessful.not())
    }

    @Test
    fun `test too long email verification code`() {
        assert(emailVerificationInteractor.validateCode("12345").isSuccessful.not())
        assert(emailVerificationInteractor.validateCode("123456").isSuccessful.not())
    }

    @Test
    fun `test email verification valid code`() {
        assert(emailVerificationInteractor.validateCode("1230").isSuccessful)
        assert(emailVerificationInteractor.validateCode("ABC0").isSuccessful)
    }
}