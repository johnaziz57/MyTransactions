package com.example.mytransactoins.ui.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytransactoins.domain.interactor.common.BlankEmailException
import com.example.mytransactoins.domain.interactor.common.InvalidEmailException
import com.example.mytransactoins.domain.interactor.login.IncorrectCredentialsException
import com.example.mytransactoins.domain.interactor.login.LoginInteractor
import com.example.mytransactoins.domain.interactor.login.UserDoesNotExistException
import com.example.mytransactoins.domain.model.NewResult
import com.example.mytransactoins.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor,
) : ViewModel() {
    val loginFormStateLiveData: LiveData<LoginFormState>
        get() = _loginFormState
    val loginResultLiveData: LiveData<Result>
        get() = _loginResult

    private val _loginFormState = MutableLiveData<LoginFormState>()
    private val _loginResult = MutableLiveData<Result>()

    fun login(email: String, password: String) {
        val emailError = getEmailErrorMessage(email)
        val passwordError = getPassowrdErrorMessage(password)
        val isValidInput = emailError == null && passwordError == null

        _loginFormState.value = LoginFormState(
            emailError = emailError,
            passwordError = passwordError
        )
        if (isValidInput) {
            when (val result = loginInteractor.login(email, password)) {
                is NewResult.Success -> {
                    _loginResult.value = Result(isSuccessful = true)
                }

                is NewResult.Error -> {
                    _loginResult.value = Result(isSuccessful = false)
                    _loginFormState.value = when (result.error) {
                        is IncorrectCredentialsException -> LoginFormState(passwordError = "Incorrect password")
                        is UserDoesNotExistException -> LoginFormState(emailError = "User doesn't exist")
                    }
                }
            }
        }
    }

    private fun getEmailErrorMessage(email: String): String? {
        when (val result = loginInteractor.validateEmail(email)) {
            is NewResult.Success -> {
                return null
            }

            is NewResult.Error -> {
                return when (result.error) {
                    is BlankEmailException -> {
                        "Blank email"
                    }

                    is InvalidEmailException -> {
                        "Invalid email format"
                    }
                }
            }
        }
    }

    private fun getPassowrdErrorMessage(password: String): String? {
        return when (val result = loginInteractor.validatePasswordLength(password)) {
            is NewResult.Success -> {
                null
            }

            is NewResult.Error -> {
                "Password too short"
            }
        }
    }
}