package com.example.mytransactoins.domain.interactor.register.email_verification

import com.example.mytransactoins.domain.model.Result

interface EmailVerificationInteractor {
    fun validateCode(code: String): Result<Unit, EmailCodeVerificationException>
}
