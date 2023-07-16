package com.example.mytransactoins.domain.interactor.register.password_validation

sealed class PasswordValidationException : Throwable()

class PasswordIsTooShortException : PasswordValidationException()
class PasswordsDoNotMatchException : PasswordValidationException()
class PasswordDoesNotHaveLettersAndDigits : PasswordValidationException()
