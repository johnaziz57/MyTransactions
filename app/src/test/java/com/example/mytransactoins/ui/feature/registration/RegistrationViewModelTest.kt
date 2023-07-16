package com.example.mytransactoins.ui.feature.registration

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mytransactoins.domain.interactor.login.LoginInteractor
import com.example.mytransactoins.domain.interactor.register.RegistrationInteractor
import com.example.mytransactoins.domain.interactor.register.email.InvalidEmailException
import com.example.mytransactoins.domain.interactor.register.email.RegistrationEmailInteractor
import com.example.mytransactoins.domain.interactor.register.email_verification.EmailVerificationInteractor
import com.example.mytransactoins.domain.interactor.register.email_verification.IncorrectCodeException
import com.example.mytransactoins.domain.interactor.register.password_validation.PasswordDoesNotHaveLettersAndDigits
import com.example.mytransactoins.domain.interactor.register.password_validation.PasswordIsTooShortException
import com.example.mytransactoins.domain.interactor.register.password_validation.PasswordsDoNotMatchException
import com.example.mytransactoins.domain.interactor.register.password_validation.ValidateRegisterPasswordInteractor
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
    private lateinit var registrationEmailInteractor: RegistrationEmailInteractor

    @Mock
    private lateinit var emailVerificationInteractor: EmailVerificationInteractor

    @Mock
    private lateinit var validateRegisterPasswordInteractor: ValidateRegisterPasswordInteractor

    @Mock
    private lateinit var registrationInteractor: RegistrationInteractor

    @Mock
    private lateinit var loginInteractor: LoginInteractor

    private lateinit var viewModel: RegistrationViewModel

    @Before
    fun setup() {
        viewModel = RegistrationViewModel(
            registrationEmailInteractor,
            emailVerificationInteractor,
            validateRegisterPasswordInteractor,
            registrationInteractor,
            loginInteractor,
        )
    }

    @Test
    fun `test submit valid email`() {
        `when`(registrationEmailInteractor.validateEmail(anyString())).thenReturn(
            Result.Success(
                Unit
            )
        )
        viewModel.submitEmail("j@e.com")
        val value = viewModel.validateEmailLiveData.getOrAwaitValue()
        assert(value.isSuccessful)
    }

    @Test
    fun `test submit invalid email`() {
        `when`(registrationEmailInteractor.validateEmail(anyString())).thenReturn(
            Result.Error(
                InvalidEmailException()
            )
        )
        viewModel.submitEmail("j@")
        val value = viewModel.validateEmailLiveData.getOrAwaitValue()
        assertFalse(value.isSuccessful)
    }

    @Test
    fun `test submit valid email verification code`() {
        `when`(emailVerificationInteractor.validateCode(anyString())).thenReturn(
            Result.Success(
                Unit
            )
        )
        viewModel.submitEmailVerificationCode("1234")
        val value = viewModel.validateEmailVerificationLiveData.getOrAwaitValue()
        assert(value.isSuccessful)
    }

    @Test
    fun `test submit invalid email verification code`() {
        `when`(emailVerificationInteractor.validateCode(anyString())).thenReturn(
            Result.Error(IncorrectCodeException())
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
        ).thenReturn(Result.Success(Unit))
        `when`(
            registrationInteractor.registerUser(
                anyString(),
                anyString()
            )
        ).thenReturn(Result.Success(Unit))
        viewModel.submitPassword("1234", "1234")
        val valuePrimary = viewModel.primaryPasswordLiveData.getOrAwaitValue()
        val valueSecondary = viewModel.secondaryPasswordLiveData.getOrAwaitValue()
        assert(valuePrimary.isSuccessful)
        assert(valueSecondary.isSuccessful)
    }

    @Test
    fun `test submit weak password`() {
        `when`(
            validateRegisterPasswordInteractor.validatePassword(
                anyString(),
                anyString()
            )
        ).thenReturn(Result.Error(PasswordDoesNotHaveLettersAndDigits()))
        viewModel.submitPassword("1234", "1")
        val value = viewModel.primaryPasswordLiveData.getOrAwaitValue()
        assertFalse(value.isSuccessful)
    }

    @Test
    fun `test submit short password`() {
        `when`(
            validateRegisterPasswordInteractor.validatePassword(
                anyString(),
                anyString()
            )
        ).thenReturn(Result.Error(PasswordIsTooShortException()))
        viewModel.submitPassword("1234", "1")
        val value = viewModel.primaryPasswordLiveData.getOrAwaitValue()
        assertFalse(value.isSuccessful)
    }

    @Test
    fun `test submit unmatching passwords`() {
        `when`(
            validateRegisterPasswordInteractor.validatePassword(
                anyString(),
                anyString()
            )
        ).thenReturn(Result.Error(PasswordsDoNotMatchException()))
        viewModel.submitPassword("1234", "1")
        val value = viewModel.secondaryPasswordLiveData.getOrAwaitValue()
        assertFalse(value.isSuccessful)
    }

}