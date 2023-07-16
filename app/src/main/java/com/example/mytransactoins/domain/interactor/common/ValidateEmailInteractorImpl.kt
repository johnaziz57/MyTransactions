package com.example.mytransactoins.domain.interactor.common

import android.util.Patterns
import com.example.mytransactoins.domain.model.Result
import javax.inject.Inject

class ValidateEmailInteractorImpl @Inject constructor() : ValidateEmailInteractor {
    override fun validateEmail(email: String): Result {
        if (email.isBlank()) {
            return Result(isSuccessful = false, message = "email is empty")
        }

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            return Result(isSuccessful = false, message = "email is invalid")
        }

        return Result(isSuccessful = true)
    }
}