package com.example.mytransactoins.domain.interactor.transaction

import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.repo.TransactionRepo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TransactionInteractorImplTest {
    @Mock
    private lateinit var transactionRepo: TransactionRepo

    private lateinit var transactionInteractor: TransactionInteractorImpl

    @Before
    fun setup() {
        transactionInteractor = TransactionInteractorImpl(transactionRepo)
    }

    @Test
    fun `test get transactions`() {
        val transactionList = listOf("transaction 1", "transaction 2")
        Mockito.`when`(transactionRepo.getTransactions())
            .thenReturn(Result.Success(transactionList))
        val result = transactionInteractor.getTransactions()
        assert(result.isSuccessful())
        assertEquals((result as? Result.Success)?.data, transactionList)
    }


}