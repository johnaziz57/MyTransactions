package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result

interface EmailInteractor {
    fun validateEmail(email: String): Result
}