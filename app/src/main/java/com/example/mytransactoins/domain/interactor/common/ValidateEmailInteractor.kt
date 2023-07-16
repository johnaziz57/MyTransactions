package com.example.mytransactoins.domain.interactor.common

import com.example.mytransactoins.domain.model.Result

interface ValidateEmailInteractor {
    fun validateEmail(email: String): Result<Unit, ValidateEmailException>
}