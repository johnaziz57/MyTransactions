package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result

class VerificationCodeInteractorImpl : VerificationCodeInteractor {
    override fun validateCode(code: String): Result {
        if (code.length != 4) {
            return Result(false, "Code is too short")
        }
        if (!code.contains("ok")) {
            return Result(false, "Code is not correct")
        }
        return Result(true)
    }
}