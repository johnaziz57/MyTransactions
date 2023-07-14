package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result

// TODO maybe extract those interactors to a common interface
interface EmailVerificationInteractor {
    fun validateCode(code: String): Result
}
