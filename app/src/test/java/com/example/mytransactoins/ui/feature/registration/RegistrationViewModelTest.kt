package com.example.mytransactoins.ui.feature.registration

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mytransactoins.domain.interactor.common.ValidateEmailInteractor
import com.example.mytransactoins.domain.interactor.register.EmailVerificationInteractor
import com.example.mytransactoins.domain.interactor.register.RegistrationInteractor
import com.example.mytransactoins.domain.interactor.register.ValidateRegisterPasswordInteractor
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.ui.feature.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegistrationViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var validateEmailInteractor: ValidateEmailInteractor

    @Mock
    private lateinit var emailVerificationInteractor: EmailVerificationInteractor

    @Mock
    private lateinit var validateRegisterPasswordInteractor: ValidateRegisterPasswordInteractor

    @Mock
    private lateinit var registrationInteractor: RegistrationInteractor

    private lateinit var viewModel: RegistrationViewModel

    @Before
    fun setup() {
        viewModel = RegistrationViewModel(
            validateEmailInteractor,
            emailVerificationInteractor,
            validateRegisterPasswordInteractor,
            registrationInteractor
        )
    }

    @Test
    fun `test submit valid email`() {
        `when`(validateEmailInteractor.validateEmail(anyString())).thenReturn(Result(isSuccessful = true))
        viewModel.submitEmail("j@e.com")
        val value = viewModel.validateEmailLiveData.getOrAwaitValue()
        assert(value.isSuccessful)
    }

    @Test
    fun `test submit invalid email`() {
        `when`(validateEmailInteractor.validateEmail(anyString())).thenReturn(
            Result(
                isSuccessful = false,
                message = ""
            )
        )
        viewModel.submitEmail("j@")
        val value = viewModel.validateEmailLiveData.getOrAwaitValue()
        assertFalse(value.isSuccessful)
    }

    @Test
    fun `test submit valid email verification code`() {
        `when`(emailVerificationInteractor.validateCode(anyString())).thenReturn(Result(isSuccessful = true))
        viewModel.submitEmailVerificationCode("1234")
        val value = viewModel.validateEmailVerificationLiveData.getOrAwaitValue()
        assert(value.isSuccessful)
    }

    @Test
    fun `test submit invalid email verification code`() {
        `when`(emailVerificationInteractor.validateCode(anyString())).thenReturn(
            Result(
                isSuccessful = false,
                message = ""
            )
        )
        viewModel.submitEmailVerificationCode("123")
        val value = viewModel.validateEmailVerificationLiveData.getOrAwaitValue()
        assertFalse(value.isSuccessful)
    }

    @Test
    fun `test submit valid password`() {
        `when`(
            validateRegisterPasswordInteractor.validatePassword(
                anyString(),
                anyString()
            )
        ).thenReturn(Result(isSuccessful = true))
        viewModel.submitPassword("1234", "1234")
        val value = viewModel.validatePasswordLiveData.getOrAwaitValue()
        assert(value.isSuccessful)
    }

    @Test
    fun `test submit invalid password`() {
        `when`(
            validateRegisterPasswordInteractor.validatePassword(
                anyString(),
                anyString()
            )
        ).thenReturn(Result(isSuccessful = false))
        viewModel.submitPassword("1234", "1")
        val value = viewModel.validatePasswordLiveData.getOrAwaitValue()
        assertFalse(value.isSuccessful)
    }

}