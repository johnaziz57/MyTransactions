package com.example.mytransactoins.domain.interactor.common

import android.util.Patterns
import com.example.mytransactoins.domain.model.NewResult
import javax.inject.Inject

class ValidateEmailInteractorImpl @Inject constructor() : ValidateEmailInteractor {
    override fun validateEmail(email: String): NewResult<Unit, ValidateEmailException> {
        if (email.isBlank()) {
            return NewResult.Error(BlankEmailException())
        }

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            return NewResult.Error(InvalidEmailException())
        }

        return NewResult.Success(Unit)
    }
}