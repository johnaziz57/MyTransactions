package com.example.mytransactoins.domain.model

sealed class NewResult<out T, E : Throwable> {
    abstract fun isSuccessful(): Boolean

    data class Success<out T, E : Throwable>(val data: T) : NewResult<T, E>() {
        override fun isSuccessful(): Boolean = true
    }

    data class Error<E : Throwable>(val error: E) : NewResult<Nothing, E>() {
        override fun isSuccessful(): Boolean = false
    }
}
