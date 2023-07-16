package com.example.mytransactoins.ui.feature.mode

data class LiveDataResult<T>(
    val isSuccessful: Boolean,
    val data: T? = null,
    val errorMessage: String? = null
)