package com.example.mytransactoins.ui.feature.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytransactoins.domain.interactor.common.ValidateEmailInteractor
import com.example.mytransactoins.domain.interactor.register.EmailVerificationInteractor
import com.example.mytransactoins.domain.interactor.register.RegistrationInteractor
import com.example.mytransactoins.domain.interactor.register.ValidateRegisterPasswordInteractor
import com.example.mytransactoins.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val validateEmailInteractor: ValidateEmailInteractor,
    private val emailVerificationInteractor: EmailVerificationInteractor,
    private val validateRegisterPasswordInteractor: ValidateRegisterPasswordInteractor,
    private val registrationInteractor: RegistrationInteractor
) : ViewModel() {
    val validateEmailLiveData: LiveData<Result>
        get() = _validateEmail

    val validateEmailVerificationLiveData: LiveData<Result>
        get() = _validateEmailVerification

    val validatePasswordLiveData: LiveData<Result>
        get() = _validatePassword


    private val _validateEmail = MutableLiveData<Result>()
    private val _validateEmailVerification = MutableLiveData<Result>()
    private val _validatePassword = MutableLiveData<Result>()

    private var email: String = ""

    fun submitEmail(email: String) {
        val result = validateEmailInteractor.validateEmail(email)
        _validateEmail.value = result
        if (result.isSuccessful) {
            this.email = email
        }
    }

    fun submitEmailVerificationCode(code: String) {
        _validateEmailVerification.value = emailVerificationInteractor.validateCode(code)
    }

    fun submitPassword(password: String, repeatedPassword: String) {
        val result = validateRegisterPasswordInteractor.validatePassword(password, repeatedPassword)
        if (result.isSuccessful) {
            registrationInteractor.registerUser(email, password)
        }
        _validatePassword.value = result
    }
}