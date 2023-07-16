package com.example.mytransactoins.ui.feature.transaction

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mytransactoins.domain.interactor.login.LoginInteractor
import com.example.mytransactoins.domain.interactor.transaction.TransactionInteractor
import com.example.mytransactoins.ui.feature.getOrAwaitValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransactionViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var loginInteractor: LoginInteractor

    @Mock
    private lateinit var transactionInteractor: TransactionInteractor

    private lateinit var viewModel: TransactionViewModel

    @Before
    fun setup() {
        viewModel = TransactionViewModel(loginInteractor, transactionInteractor)
    }

    @Test
    fun `test loading transaction list`() {
        val transactionList = listOf("transaction 1", "transaction 2")
        `when`(transactionInteractor.getTransactions()).thenReturn(transactionList)

        viewModel.loadTransactions()
        val value = viewModel.transactionListLiveData.getOrAwaitValue()
        assert(value == transactionList)
    }
}