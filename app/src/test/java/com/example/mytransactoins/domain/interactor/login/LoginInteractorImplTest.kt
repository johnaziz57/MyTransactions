package com.example.mytransactoins.domain.interactor.login

import com.example.mytransactoins.domain.interactor.common.InvalidEmailException
import com.example.mytransactoins.domain.interactor.common.ValidateEmailInteractor
import com.example.mytransactoins.domain.model.NewResult
import com.example.mytransactoins.domain.model.User
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
        `when`(validateEmailInteractor.validateEmail(validEmail)).thenReturn(NewResult.Success(Unit))
        `when`(validateEmailInteractor.validateEmail(invalidEmail)).thenReturn(
            NewResult.Error(
                InvalidEmailException()
            )
        )

        assert(loginInteractor.validateEmail(validEmail) is NewResult.Success)
        assert(loginInteractor.validateEmail(invalidEmail) is NewResult.Error)
    }

    @Test
    fun `test validatePasswordLength`() {
        val validPassword = "123456"
        val invalidPassword = "1"
        assert(loginInteractor.validatePasswordLength(validPassword) is NewResult.Success)
        assert(loginInteractor.validatePasswordLength(invalidPassword) is NewResult.Error)
    }

    @Test
    fun `test successful login`() {
        val email = "j@e.com"
        `when`(userRepo.logIn(anyString(), anyString())).thenReturn(NewResult.Success(User(email)))
        // TODO test results
        assert(loginInteractor.login(email, "password") is NewResult.Success)
    }

    @Test
    fun `test unsuccessful login`() {
        `when`(userRepo.logIn(anyString(), anyString())).thenReturn(
            NewResult.Error(
                IncorrectCredentialsException()
            )
        )
        assert(loginInteractor.login("j@e.com", "password") is NewResult.Error)
    }
}