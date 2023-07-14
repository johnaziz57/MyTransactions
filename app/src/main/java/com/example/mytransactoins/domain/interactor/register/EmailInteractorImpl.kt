package com.example.mytransactoins.domain.interactor.register

import android.util.Patterns
import com.example.mytransactoins.domain.model.Result
import javax.inject.Inject

class EmailInteractorImpl @Inject constructor() : EmailInteractor {
    override fun validateEmail(email: String): Result {
        if (email.isBlank()) {
            return Result(isSuccess = false, message = "email is empty")
        }

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            return Result(isSuccess = false, message = "email is invalid")
        }

        return Result(isSuccess = true)
    }
}