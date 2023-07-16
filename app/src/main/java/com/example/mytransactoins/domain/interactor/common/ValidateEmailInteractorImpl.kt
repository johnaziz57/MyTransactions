package com.example.mytransactoins.domain.interactor.common

import android.util.Patterns
import com.example.mytransactoins.domain.model.Result
import javax.inject.Inject

class ValidateEmailInteractorImpl @Inject constructor() : ValidateEmailInteractor {
    override fun validateEmail(email: String): Result<Unit, ValidateEmailException> {
        if (email.isBlank()) {
            return Result.Error(BlankEmailException())
        }

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            return Result.Error(InvalidEmailException())
        }

        return Result.Success(Unit)
    }
}