package com.example.mytransactoins.domain.utils

import android.util.Patterns
import com.example.mytransactoins.domain.model.Result

object Utils {
    fun validateEmail(email: String): Result {
        if (email.isBlank()) {
            return Result(isSuccessful = false, message = "email is empty")
        }

        if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            return Result(isSuccessful = false, message = "email is invalid")
        }

        return Result(isSuccessful = true)
    }
}
