package com.example.mytransactoins.ui.feature.entry

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mytransactoins.domain.interactor.entry.EntryInteractor
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
class EntryViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var entryInteractor: EntryInteractor

    private lateinit var viewModel: EntryViewModel

    @Before
    fun setup() {
        viewModel = EntryViewModel(entryInteractor)
    }

    @Test
    fun `test isLoggedIn`() {
        `when`(entryInteractor.isLoggedIn()).thenReturn(true)
        viewModel.isLoggedIn()
        val value = viewModel.isLoggedInLiveData.getOrAwaitValue()
        assert(value.data ?: false)
    }
}