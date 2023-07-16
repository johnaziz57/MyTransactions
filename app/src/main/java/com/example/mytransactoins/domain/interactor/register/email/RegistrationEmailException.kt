package com.example.mytransactoins.domain.interactor.register.email

sealed class RegistrationEmailException : Throwable()

class BlankEmailException : RegistrationEmailException()
class InvalidEmailException : RegistrationEmailException()
class AlreadyRegisteredException : RegistrationEmailException()