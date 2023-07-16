package com.example.mytransactoins.domain.interactor.register.email

import com.example.mytransactoins.domain.interactor.common.ValidateEmailFormatInteractor
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.repo.UserRepo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import com.example.mytransactoins.domain.interactor.common.BlankEmailException as ValidationBlankEmailException

@RunWith(MockitoJUnitRunner::class)
class RegistrationEmailInteractorImplTest {

    @Mock
    private lateinit var validateEmailFormatInteractor: ValidateEmailFormatInteractor

    @Mock
    private lateinit var userRepo: UserRepo

    private lateinit var registrationEmailInteractor: RegistrationEmailInteractorImpl

    @Before
    fun setup() {
        registrationEmailInteractor =
            RegistrationEmailInteractorImpl(validateEmailFormatInteractor, userRepo)
    }

    @Test
    fun `test validation fails when email format is wrong`() {
        `when`(validateEmailFormatInteractor.validateEmail(anyString())).thenReturn(
            Result.Error(
                ValidationBlankEmailException()
            )
        )
        assertFalse(registrationEmailInteractor.validateEmail("email").isSuccessful())
    }

    @Test
    fun `test validation fails when user is already subscribed`() {
        `when`(validateEmailFormatInteractor.validateEmail(anyString())).thenReturn(
            Result.Success(
                Unit
            )
        )
        `when`(userRepo.hasUser(anyString())).thenReturn(true)
        assertFalse(registrationEmailInteractor.validateEmail("email").isSuccessful())
    }

    @Test
    fun `test validation succeeds when user email is correct and not registered before`() {
        `when`(validateEmailFormatInteractor.validateEmail(anyString())).thenReturn(
            Result.Success(
                Unit
            )
        )
        `when`(userRepo.hasUser(anyString())).thenReturn(false)
        assert(registrationEmailInteractor.validateEmail("email").isSuccessful())
    }

}