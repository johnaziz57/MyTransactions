package com.example.mytransactoins.domain.interactor.register

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ValidateRegisterPasswordInteractorImplTest {

    private lateinit var validateRegistrationInteractor: ValidateRegisterPasswordInteractorImpl

    @Before
    fun setup() {
        validateRegistrationInteractor = ValidateRegisterPasswordInteractorImpl()
    }

    @Test
    fun `test too short password`() {
        listOf("passw", "p", "", "1234").forEach {
            assert(validatePassword(it, it).not())
        }
    }

    @Test
    fun `test repeated password doesn't match`() {
        listOf(Pair("password", "passkey"), Pair("password1", "password2")).forEach {
            assert(validatePassword(it.first, it.second).not())
        }
    }

    @Test
    fun `test password doesn't have letters and digits`() {
        listOf("password", "12345678").forEach {
            assert(validatePassword(it, it).not())
        }
    }

    @Test
    fun `test valid password`() {
        listOf("password1", "12345678p").forEach {
            assert(validatePassword(it, it))
        }
    }

    private fun validatePassword(password: String, repeatedPassword: String): Boolean {
        return validateRegistrationInteractor.validatePassword(
            password,
            repeatedPassword
        ).isSuccessful
    }

}