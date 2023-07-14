package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result
import javax.inject.Inject

class PasswordInteractorImpl @Inject constructor() : PasswordInteractor {
    override fun validatePassword(password: String, confirmedPassword: String): Result {
        if (password.length < 6) {
            return Result(isSuccess = false, message = "Password is too short")
        }
        if (password != confirmedPassword) {
            return Result(isSuccess = false, message = "Password don't match")
        }

        val containsDigitAndLetters =
            password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsDigitAndLetters) {
            return Result(
                isSuccess = false,
                message = "Password doesn't contain digits and letters"
            )
        }
        return Result(isSuccess = true)
    }
}