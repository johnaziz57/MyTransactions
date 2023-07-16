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
import com.example.mytransactoins.ui.feature.mode.LiveDataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor,
) : ViewModel() {
    val emailLiveData: LiveData<LiveDataResult<Unit>>
        get() = _email
    val passwordLiveData: LiveData<LiveDataResult<Unit>>
        get() = _password
    val loginResultLiveData: LiveData<LiveDataResult<Unit>>
        get() = _loginResult

    private val _email = MutableLiveData<LiveDataResult<Unit>>()
    private val _password = MutableLiveData<LiveDataResult<Unit>>()
    private val _loginResult = MutableLiveData<LiveDataResult<Unit>>()

    fun login(email: String, password: String) {
        _email.value = validateEmail(email)
        _password.value = validatePassword(password)
        val isValidInput =
            _email.value?.isSuccessful ?: false && _password.value?.isSuccessful ?: false

        if (isValidInput) {
            when (val result = loginInteractor.login(email, password)) {
                is Result.Success -> {
                    _loginResult.value = LiveDataResult(isSuccessful = true)
                }

                is Result.Error -> {
                    _loginResult.value = LiveDataResult(isSuccessful = false)
                    when (result.error) {
                        is IncorrectCredentialsException -> _password.value = LiveDataResult(
                            isSuccessful = false,
                            errorMessage = "Incorrect password"
                        )

                        is UserDoesNotExistException -> _password.value = LiveDataResult(
                            isSuccessful = false,
                            errorMessage = "User doesn't exist"
                        )
                    }
                }
            }
        }
    }

    private fun validateEmail(email: String): LiveDataResult<Unit> {
        return when (val result = loginInteractor.validateEmail(email)) {
            is Result.Success -> {
                LiveDataResult(isSuccessful = true)
            }

            is Result.Error -> {
                when (result.error) {
                    is BlankEmailException -> LiveDataResult(
                        isSuccessful = false,
                        errorMessage = "Empty email"
                    )

                    is InvalidEmailException -> LiveDataResult(
                        isSuccessful = false,
                        errorMessage = "Invalid email"
                    )
                }
            }
        }
    }

    private fun validatePassword(password: String): LiveDataResult<Unit> {
        return when (loginInteractor.validatePasswordLength(password)) {
            is Result.Success -> {
                LiveDataResult(isSuccessful = true)
            }

            is Result.Error ->
                LiveDataResult(isSuccessful = false, errorMessage = "Password too short")

        }
    }
}