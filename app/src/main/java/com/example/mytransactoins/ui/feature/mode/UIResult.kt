package com.example.mytransactoins.ui.feature.mode

data class UIResult<T>(
    val isSuccessful: Boolean,
    val data: T? = null,
    val errorMessage: String? = null
)