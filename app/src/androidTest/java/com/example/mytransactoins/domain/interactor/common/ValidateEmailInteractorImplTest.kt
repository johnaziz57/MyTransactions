package com.example.mytransactoins.domain.interactor.common

import com.example.mytransactoins.domain.model.NewResult
import org.junit.Test

/**
 * ValidateEmailInteractor relies on Android.Pattern to match the email
 * This requires running the logic as part of AndroidTest.
 */
internal class ValidateEmailInteractorImplTest {

    @Test
    fun testValidEmails() {
        val validateEmailInteractor = ValidateEmailInteractorImpl()
        fun testValidEmail(email: String) {
            assert(validateEmailInteractor.validateEmail(email) is NewResult.Success)
        }

        listOf(
            "j@e.com",
            "j.1@e.com",
            "something_something_something@something.something.something"
        ).forEach {
            testValidEmail(it)
        }
    }

    @Test
    fun testInValidEmails() {
        val validateEmailInteractor = ValidateEmailInteractorImpl()
        fun testInvalidEmail(email: String) {
            assert(validateEmailInteractor.validateEmail(email) is NewResult.Error)
        }

        listOf("", "j.1@", "@something.com", "123@.com").forEach {
            testInvalidEmail(it)
        }
    }
}