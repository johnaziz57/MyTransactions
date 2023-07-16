package com.example.mytransactoins.domain.interactor.common

import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.utils.Utils
import javax.inject.Inject

class ValidateEmailInteractorImpl @Inject constructor() : ValidateEmailInteractor {
    override fun validateEmail(email: String): Result {
        return Utils.validateEmail(email)
    }
}