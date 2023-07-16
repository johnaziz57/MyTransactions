package com.example.mytransactoins.domain.interactor.common


sealed class ValidateEmailException : Throwable()

class BlankEmailException : ValidateEmailException()
class InvalidEmailException : ValidateEmailException()

