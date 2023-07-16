package com.example.mytransactoins.ui.feature.transaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytransactoins.domain.interactor.login.LoginInteractor
import com.example.mytransactoins.domain.interactor.transaction.TransactionInteractor
import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.ui.feature.mode.LiveDataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor,
    private val transactionInteractor: TransactionInteractor
) : ViewModel() {

    val transactionListLiveData: LiveData<LiveDataResult<List<String>>>
        get() = _transactionList

    private val _transactionList = MutableLiveData<LiveDataResult<List<String>>>()
    fun loadTransactions() {
        transactionInteractor.getTransactions().let {
            _transactionList.value = when (it) {
                is Result.Error -> LiveDataResult(
                    isSuccessful = false,
                    errorMessage = "Something went wrong"
                )

                is Result.Success -> LiveDataResult(isSuccessful = true, data = it.data)
            }
        }
    }

    fun logout() {
        loginInteractor.logout()
    }
}