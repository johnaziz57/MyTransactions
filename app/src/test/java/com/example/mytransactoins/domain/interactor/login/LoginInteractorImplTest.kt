package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.interactor.common.ValidateEmailInteractor
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.repo.UserRepo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginInteractorImplTest {
    @Mock
    private lateinit var userRepo: UserRepo

    @Mock
    private lateinit var validateEmailInteractor: ValidateEmailInteractor

    private lateinit var loginInteractor: LoginInteractorImpl

    @Before
    fun setup() {
        loginInteractor = LoginInteractorImpl(userRepo, validateEmailInteractor)
    }

    @Test
    fun `test validateEmail`() {
        val validEmail = "j@e.com"
        val invalidEmail = "@e."
        `when`(validateEmailInteractor.validateEmail(validEmail)).thenReturn(Result(isSuccessful = true))
        `when`(validateEmailInteractor.validateEmail(invalidEmail)).thenReturn(Result(isSuccessful = false))

        assert(loginInteractor.validateEmail(validEmail).isSuccessful)
        assertFalse(loginInteractor.validateEmail(invalidEmail).isSuccessful)
    }

    @Test
    fun `test validatePasswordLength`() {
        val validPassword = "123456"
        val invalidPassword = "1"
        assert(loginInteractor.validatePasswordLength(validPassword).isSuccessful)
        assertFalse(loginInteractor.validatePasswordLength(invalidPassword).isSuccessful)
    }

    @Test
    fun `test successful login`() {
        `when`(userRepo.logIn(anyString(), anyString())).thenReturn(Result(isSuccessful = true))
        assert(loginInteractor.login("j@e.com", "password").isSuccessful)
    }

    @Test
    fun `test unsuccessful login`() {
        `when`(userRepo.logIn(anyString(), anyString())).thenReturn(Result(isSuccessful = false))
        assertFalse(loginInteractor.login("j@e.com", "password").isSuccessful)
    }
}