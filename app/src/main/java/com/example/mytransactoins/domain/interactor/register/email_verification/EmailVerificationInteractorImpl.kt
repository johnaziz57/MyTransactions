package com.example.mytransactoins.domain.interactor.register.email_verification

import com.example.mytransactoins.domain.model.NewResult
import javax.inject.Inject

class EmailVerificationInteractorImpl @Inject constructor() : EmailVerificationInteractor {
    override fun validateCode(code: String): NewResult<Unit, EmailCodeVerificationException> {
        if (code.length != 4) {
            return NewResult.Error(CodeTooShortException())
        }
        if (!code.contains("0")) {
            return NewResult.Error(IncorrectCodeException())
        }
        return NewResult.Success(Unit)
    }
}