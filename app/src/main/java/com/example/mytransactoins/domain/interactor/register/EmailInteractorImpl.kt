package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.utils.Utils
import javax.inject.Inject

class EmailInteractorImpl @Inject constructor() : EmailInteractor {
    override fun validateEmail(email: String): Result {
        return Utils.validateEmail(email)
    }
}