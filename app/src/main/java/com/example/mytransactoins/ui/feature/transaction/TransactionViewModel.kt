package com.example.mytransactoins.ui.feature.transaction

import androidx.lifecycle.ViewModel
import com.example.mytransactoins.domain.interactor.login.LoginInteractor
import com.example.mytransactoins.domain.interactor.transaction.TransactionInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor,
    private val transactionInteractor: TransactionInteractor
) : ViewModel() {

    fun logout() {
        loginInteractor.logout()
    }
}