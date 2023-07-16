package com.example.mytransactoins.ui.feature.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mytransactoins.domain.interactor.login.LoginInteractor
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.ui.feature.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginInteractor: LoginInteractor

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        viewModel = LoginViewModel(loginInteractor)
    }

    @Test
    fun `test login with invalid email`() {
        `when`(loginInteractor.validateEmail(anyString())).thenReturn(
            Result(
                isSuccessful = false,
                message = "invalid email"
            )
        )
        `when`(loginInteractor.validatePasswordLength(anyString())).thenReturn(Result(isSuccessful = true))
        viewModel.login("", "password")
        val value = viewModel.loginFormStateLiveData.getOrAwaitValue()
        assert(value.emailError != null)
    }

    @Test
    fun `test login with invalid password`() {
        `when`(loginInteractor.validateEmail(anyString())).thenReturn(Result(isSuccessful = true))
        `when`(loginInteractor.validatePasswordLength(anyString())).thenReturn(
            Result(
                isSuccessful = false,
                message = "invalid password"
            )
        )
        viewModel.login("j@e.com", "pass")
        val value = viewModel.loginFormStateLiveData.getOrAwaitValue()
        assert(value.passwordError != null)
    }

    @Test
    fun `test login with valid email and password`() {
        `when`(loginInteractor.validateEmail(anyString())).thenReturn(Result(isSuccessful = true))
        `when`(loginInteractor.validatePasswordLength(anyString())).thenReturn(Result(isSuccessful = true))
        `when`(
            loginInteractor.login(
                anyString(),
                anyString()
            )
        ).thenReturn(Result(isSuccessful = true))
        viewModel.login("j@e.com", "password")
        val value = viewModel.loginFormStateLiveData.getOrAwaitValue()
        assert(value.emailError == null)
        assert(value.passwordError == null)
    }

    @Test
    fun `test login with valid email and password and successful login`() {
        `when`(loginInteractor.validateEmail(anyString())).thenReturn(Result(isSuccessful = true))
        `when`(loginInteractor.validatePasswordLength(anyString())).thenReturn(Result(isSuccessful = true))
        `when`(
            loginInteractor.login(
                anyString(),
                anyString()
            )
        ).thenReturn(Result(isSuccessful = true))
        viewModel.login("j@e.com", "password")
        val value = viewModel.loginResultLiveData.getOrAwaitValue()
        assert(value.isSuccessful)
    }

    @Test
    fun `test login with valid email and password and failed login`() {
        `when`(loginInteractor.validateEmail(anyString())).thenReturn(Result(isSuccessful = true))
        `when`(loginInteractor.validatePasswordLength(anyString())).thenReturn(Result(isSuccessful = true))
        `when`(
            loginInteractor.login(
                anyString(),
                anyString()
            )
        ).thenReturn(Result(isSuccessful = false))
        viewModel.login("j@e.com", "password")
        val value = viewModel.loginResultLiveData.getOrAwaitValue()
        assertFalse(value.isSuccessful)
    }
}