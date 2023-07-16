package com.example.mytransactoins.ui.feature.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytransactoins.domain.interactor.common.BlankEmailException
import com.example.mytransactoins.domain.interactor.common.InvalidEmailException
import com.example.mytransactoins.domain.interactor.common.ValidateEmailFormatInteractor
import com.example.mytransactoins.domain.interactor.login.LoginInteractor
import com.example.mytransactoins.domain.interactor.register.RegistrationInteractor
import com.example.mytransactoins.domain.interactor.register.email_verification.CodeTooShortException
import com.example.mytransactoins.domain.interactor.register.email_verification.EmailVerificationInteractor
import com.example.mytransactoins.domain.interactor.register.email_verification.IncorrectCodeException
import com.example.mytransactoins.domain.interactor.register.password_validation.PasswordDoesNotHaveLettersAndDigits
import com.example.mytransactoins.domain.interactor.register.password_validation.PasswordIsTooShortException
import com.example.mytransactoins.domain.interactor.register.password_validation.PasswordValidationException
import com.example.mytransactoins.domain.interactor.register.password_validation.PasswordsDoNotMatchException
import com.example.mytransactoins.domain.interactor.register.password_validation.ValidateRegisterPasswordInteractor
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.ui.feature.mode.LiveDataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateEmailFormatInteractor: ValidateEmailFormatInteractor,
    private val emailVerificationInteractor: EmailVerificationInteractor,
    private val validateRegisterPasswordInteractor: ValidateRegisterPasswordInteractor,
    private val registrationInteractor: RegistrationInteractor,
    private val loginInteractor: LoginInteractor
) : ViewModel() {
    val validateEmailLiveData: LiveData<LiveDataResult<Unit>>
        get() = _validateEmail
    val validateEmailVerificationLiveData: LiveData<LiveDataResult<Unit>>
        get() = _validateEmailVerification
    val primaryPasswordLiveData: LiveData<LiveDataResult<Unit>>
        get() = _primaryPassword
    val secondaryPasswordLiveData: LiveData<LiveDataResult<Unit>>
        get() = _secondaryPassword

    val loginLiveData: LiveData<LiveDataResult<Unit>>
        get() = _login


    private val _validateEmail = MutableLiveData<LiveDataResult<Unit>>()
    private val _validateEmailVerification = MutableLiveData<LiveDataResult<Unit>>()
    private val _primaryPassword = MutableLiveData<LiveDataResult<Unit>>()
    private val _secondaryPassword = MutableLiveData<LiveDataResult<Unit>>()
    private val _login = MutableLiveData<LiveDataResult<Unit>>()

    private var email: String = ""

    fun submitEmail(email: String) {
        when (val result = validateEmailFormatInteractor.validateEmail(email)) {
            is Result.Success -> {
                _validateEmail.value = LiveDataResult(isSuccessful = true)
            }

            is Result.Error -> {
                when (result.error) {
                    is BlankEmailException -> {
                        _validateEmail.value =
                            LiveDataResult(isSuccessful = false, errorMessage = "Blank email")
                    }

                    is InvalidEmailException -> {
                        _validateEmail.value =
                            LiveDataResult(
                                isSuccessful = false,
                                errorMessage = "Invalid email format"
                            )
                    }
                }
            }
        }
    }

    fun submitEmailVerificationCode(code: String) {
        _validateEmailVerification.value = emailVerificationInteractor.validateCode(code).let {
            when (it) {
                is Result.Success -> LiveDataResult(isSuccessful = true)
                is Result.Error -> {
                    when (it.error) {
                        is CodeTooShortException -> LiveDataResult(
                            isSuccessful = false,
                            errorMessage = "Code too short"
                        )

                        is IncorrectCodeException -> LiveDataResult(
                            isSuccessful = false,
                            errorMessage = "Incorrect code"
                        )
                    }
                }
            }
        }
    }

    fun submitPassword(password: String, repeatedPassword: String) {
        when (val result =
            validateRegisterPasswordInteractor.validatePassword(password, repeatedPassword)) {
            is Result.Success -> {
                _primaryPassword.value = LiveDataResult(isSuccessful = true)
                _secondaryPassword.value = LiveDataResult(isSuccessful = true)
                registrationInteractor.registerUser(email, password).let {
                    if (it is Result.Success) {
                        login(email, password)
                    }
                }
            }

            is Result.Error -> {
                handlePasswordError(result.error)
            }
        }
    }

    private fun login(email: String, password: String) {
        loginInteractor.login(email, password).let {
            when (it) {
                is Result.Success -> _login.value = LiveDataResult(isSuccessful = true)
                else -> {}
            }
        }
    }

    private fun handlePasswordError(passwordValidationException: PasswordValidationException) {
        when (passwordValidationException) {
            is PasswordDoesNotHaveLettersAndDigits -> {
                _primaryPassword.value = LiveDataResult(
                    isSuccessful = false,
                    errorMessage = "Password doesn't have both letters and digits"
                )
            }

            is PasswordIsTooShortException -> {
                _primaryPassword.value =
                    LiveDataResult(isSuccessful = false, errorMessage = "Password is too short")
            }

            is PasswordsDoNotMatchException -> {
                _secondaryPassword.value =
                    LiveDataResult(isSuccessful = false, errorMessage = "Passwords don't match")
            }
        }
    }
}