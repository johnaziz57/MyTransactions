package com.example.mytransactoins.domain.model

sealed class Result<out T, E : Throwable> {
    abstract fun isSuccessful(): Boolean

    data class Success<out T, E : Throwable>(val data: T) : Result<T, E>() {
        override fun isSuccessful(): Boolean = true
    }

    data class Error<E : Throwable>(val error: E) : Result<Nothing, E>() {
        override fun isSuccessful(): Boolean = false
    }
}
