package com.example.mytransactoins.ui.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytransactoins.domain.interactor.common.BlankEmailException
import com.example.mytransactoins.domain.interactor.common.InvalidEmailException
import com.example.mytransactoins.domain.interactor.login.IncorrectCredentialsException
import com.example.mytransactoins.domain.interactor.login.LoginInteractor
import com.example.mytransactoins.domain.interactor.login.UserDoesNotExistException
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.ui.feature.mode.UIResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor,
) : ViewModel() {
    val loginFormStateLiveData: LiveData<LoginFormState>
        get() = _loginFormState
    val loginResultLiveData: LiveData<UIResult<Unit>>
        get() = _loginResult

    private val _loginFormState = MutableLiveData<LoginFormState>()
    private val _loginResult = MutableLiveData<UIResult<Unit>>()

    fun login(email: String, password: String) {
        val emailError = validateEmail(email)
        val passwordError = validatePassword(password)
        val isValidInput = emailError == null && passwordError == null

        _loginFormState.value = LoginFormState(
            emailError = emailError,
            passwordError = passwordError
        )
        if (isValidInput) {
            when (val result = loginInteractor.login(email, password)) {
                is Result.Success -> {
                    _loginResult.value = UIResult(isSuccessful = true)
                }

                is Result.Error -> {
                    _loginResult.value = UIResult(isSuccessful = false)
                    _loginFormState.value = when (result.error) {
                        is IncorrectCredentialsException -> LoginFormState(passwordError = "Incorrect password")
                        is UserDoesNotExistException -> LoginFormState(emailError = "User doesn't exist")
                    }
                }
            }
        }
    }

    private fun validateEmail(email: String): String? {
        when (val result = loginInteractor.validateEmail(email)) {
            is Result.Success -> {
                return null
            }

            is Result.Error -> {
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

    private fun validatePassword(password: String): String? {
        return when (loginInteractor.validatePasswordLength(password)) {
            is Result.Success -> {
                null
            }

            is Result.Error -> {
                "Password too short"
            }
        }
    }
}