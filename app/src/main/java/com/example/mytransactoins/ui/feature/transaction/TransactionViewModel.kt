package com.example.mytransactoins.ui.feature.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    val transactionListLiveData: LiveData<List<String>>
        get() = _transactionList

    private val _transactionList = MutableLiveData<List<String>>()
    fun loadTransactions() {
        _transactionList.value = transactionInteractor.getTransactions()
    }

    fun logout() {
        loginInteractor.logout()
    }
}