package com.example.mytransactoins.ui.feature.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytransactoins.domain.interactor.common.BlankEmailException
import com.example.mytransactoins.domain.interactor.common.InvalidEmailException
import com.example.mytransactoins.domain.interactor.common.ValidateEmailInteractor
import com.example.mytransactoins.domain.interactor.login.LoginInteractor
import com.example.mytransactoins.domain.interactor.register.RegistrationInteractor
import com.example.mytransactoins.domain.interactor.register.email_verification.CodeTooShortException
import com.example.mytransactoins.domain.interactor.register.email_verification.EmailVerificationInteractor
import com.example.mytransactoins.domain.interactor.register.email_verification.IncorrectCodeException
import com.example.mytransactoins.domain.interactor.register.password_validation.PasswordDoesNotHaveLettersAndDigits
import com.example.mytransactoins.domain.interactor.register.password_validation.PasswordIsTooShortException
import com.example.mytransactoins.domain.interactor.register.password_validation.PasswordsDoNotMatchException
import com.example.mytransactoins.domain.interactor.register.password_validation.ValidateRegisterPasswordInteractor
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.ui.feature.mode.UIResult
import com.example.mytransactoins.ui.feature.registration.password.PasswordFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateEmailInteractor: ValidateEmailInteractor,
    private val emailVerificationInteractor: EmailVerificationInteractor,
    private val validateRegisterPasswordInteractor: ValidateRegisterPasswordInteractor,
    private val registrationInteractor: RegistrationInteractor,
    private val loginInteractor: LoginInteractor
) : ViewModel() {
    val validateEmailLiveData: LiveData<UIResult<Unit>>
        get() = _validateEmail

    val validateEmailVerificationLiveData: LiveData<UIResult<Unit>>
        get() = _validateEmailVerification

    val validatePasswordLiveData: LiveData<PasswordFormState>
        get() = _validatePassword


    private val _validateEmail = MutableLiveData<UIResult<Unit>>()
    private val _validateEmailVerification = MutableLiveData<UIResult<Unit>>()
    private val _validatePassword = MutableLiveData<PasswordFormState>()

    private var email: String = ""

    fun submitEmail(email: String) {
        when (val result = validateEmailInteractor.validateEmail(email)) {
            is Result.Success -> {
                _validateEmail.value = UIResult(isSuccessful = true)
            }

            is Result.Error -> {
                when (result.error) {
                    is BlankEmailException -> {
                        _validateEmail.value =
                            UIResult(isSuccessful = false, errorMessage = "Blank email")
                    }

                    is InvalidEmailException -> {
                        _validateEmail.value =
                            UIResult(isSuccessful = false, errorMessage = "Invalid email format")
                    }
                }
            }
        }
    }

    fun submitEmailVerificationCode(code: String) {
        _validateEmailVerification.value = emailVerificationInteractor.validateCode(code).let {
            when (it) {
                is Result.Success -> UIResult(isSuccessful = true)
                is Result.Error -> {
                    when (it.error) {
                        is CodeTooShortException -> UIResult(
                            isSuccessful = false,
                            errorMessage = "Code too short"
                        )

                        is IncorrectCodeException -> UIResult(
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
                _validatePassword.value = PasswordFormState()
                registrationInteractor.registerUser(email, password).let {
                    if (it is Result.Success) {
                        loginInteractor.login(email, password)
                    }
                }
            }

            is Result.Error -> {
                _validatePassword.value = when (result.error) {
                    is PasswordDoesNotHaveLettersAndDigits -> PasswordFormState(primaryPasswordError = "Password doesn't have both letters and digits")
                    is PasswordIsTooShortException -> PasswordFormState(primaryPasswordError = "Password is too short")
                    is PasswordsDoNotMatchException -> PasswordFormState(secondaryPasswordError = "Passwords don't match")
                }
            }
        }
    }
}