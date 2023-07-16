package com.example.mytransactoins.domain.interactor.register.email_verification

sealed class EmailCodeVerificationException : Throwable()

class CodeTooShortException : EmailCodeVerificationException()
class IncorrectCodeException : EmailCodeVerificationException()
