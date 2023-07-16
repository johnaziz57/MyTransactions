package com.example.mytransactoins.domain.interactor.login

sealed class LoginException : Throwable()
class UserDoesNotExistException : LoginException()
class IncorrectCredentialsException : LoginException()
