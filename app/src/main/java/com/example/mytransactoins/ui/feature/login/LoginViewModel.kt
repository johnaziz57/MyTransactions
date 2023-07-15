package com.example.mytransactoins.ui.feature.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytransactoins.domain.interactor.login.LoginInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor
) : ViewModel() {
    val loginFormState: LiveData<LoginFormState>
        get() = _loginForm

    private val _loginForm = MutableLiveData<LoginFormState>()

    fun login(username: String, password: String) {

    }

    fun loginDataChanged(username: String, password: String) {

    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}