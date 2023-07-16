package com.example.mytransactoins.domain.interactor.register.email_verification

import com.example.mytransactoins.domain.model.NewResult

interface EmailVerificationInteractor {
    fun validateCode(code: String): NewResult<Unit, EmailCodeVerificationException>
}
