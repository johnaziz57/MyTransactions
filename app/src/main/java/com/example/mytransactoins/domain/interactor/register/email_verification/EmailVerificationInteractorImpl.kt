package com.example.mytransactoins.domain.interactor.register.email_verification

import com.example.mytransactoins.domain.model.Result
import javax.inject.Inject

class EmailVerificationInteractorImpl @Inject constructor() : EmailVerificationInteractor {
    override fun validateCode(code: String): Result<Unit, EmailCodeVerificationException> {
        if (code.length != 4) {
            return Result.Error(CodeTooShortException())
        }
        if (!code.contains("0")) {
            return Result.Error(IncorrectCodeException())
        }
        return Result.Success(Unit)
    }
}