package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result
import javax.inject.Inject

class EmailVerificationInteractorImpl @Inject constructor() : EmailVerificationInteractor {
    override fun validateCode(code: String): Result {
        if (code.length != 4) {
            return Result(false, "Code is too short")
        }
        if (!code.contains("0")) {
            return Result(false, "Code is not correct")
        }
        return Result(true)
    }
}