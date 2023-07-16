package com.example.mytransactoins.ui.feature.registration.password

data class PasswordFormState(
    val primaryPasswordError: String? = null,
    val secondaryPasswordError: String? = null,
    val isValid: Boolean = primaryPasswordError == null && secondaryPasswordError == null
)