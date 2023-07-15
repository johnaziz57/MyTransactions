package com.example.mytransactoins.ui.feature.login

data class LoginFormState(
    val emailError: String? = null,
    val passwordError: String? = null,
    val isValid: Boolean = false
)
