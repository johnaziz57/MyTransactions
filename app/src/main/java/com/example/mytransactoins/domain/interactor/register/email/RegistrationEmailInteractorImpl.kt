package com.example.mytransactoins.domain.interactor.register.email

import com.example.mytransactoins.domain.interactor.common.ValidateEmailFormatInteractor
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.repo.UserRepo
import javax.inject.Inject
import com.example.mytransactoins.domain.interactor.common.BlankEmailException as ValidationBlankEmailException
import com.example.mytransactoins.domain.interactor.common.InvalidEmailException as ValidationInvalidEmailException

class RegistrationEmailInteractorImpl @Inject constructor(
    private val validateEmailFormatInteractor: ValidateEmailFormatInteractor,
    private val userRepo: UserRepo
) : RegistrationEmailInteractor {

    override fun validateEmail(email: String): Result<Unit, RegistrationEmailException> {
        val formatValidationResult = validateEmailFormatInteractor.validateEmail(email)
        if (formatValidationResult is Result.Error) {
            return when (formatValidationResult.error) {
                is ValidationBlankEmailException -> Result.Error(BlankEmailException())
                is ValidationInvalidEmailException -> Result.Error(InvalidEmailException())
            }
        }
        if (userRepo.hasUser(email)) {
            return Result.Error(AlreadyRegisteredException())
        }
        return Result.Success(Unit)
    }
}