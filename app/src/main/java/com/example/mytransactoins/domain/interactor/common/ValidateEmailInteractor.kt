package com.example.mytransactoins.domain.interactor.common

import com.example.mytransactoins.domain.model.NewResult

interface ValidateEmailInteractor {
    fun validateEmail(email: String): NewResult<Unit, ValidateEmailException>
}