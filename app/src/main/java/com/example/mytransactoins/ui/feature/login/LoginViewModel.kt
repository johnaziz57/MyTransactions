package com.example.mytransactoins.ui.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytransactoins.domain.interactor.login.LoginInteractor
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
        val emailError = loginInteractor.validateEmail(email).message
        val passwordError = loginInteractor.validatePasswordLength(password).message
        val isValidInput = emailError == null && passwordError == null

        _loginFormState.value = LoginFormState(
            emailError = loginInteractor.validateEmail(email).message,
            passwordError = loginInteractor.validatePasswordLength(password).message,
        )
        if (isValidInput) {
            val result = loginInteractor.login(email, password)
            // TODO add specific login error
            if (!result.isSuccessful) {
                _loginFormState.value =
                    LoginFormState(emailError = result.message, passwordError = result.message)
            }
            _loginResult.value = result
        }
    }
}