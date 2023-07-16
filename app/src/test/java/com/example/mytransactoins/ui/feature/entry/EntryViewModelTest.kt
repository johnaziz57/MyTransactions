package com.example.mytransactoins.ui.feature.entry

import com.example.mytransactoins.domain.interactor.entry.EntryInteractor
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EntryViewModelTest {
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
        assert(viewModel.isLoggedIn())
    }
}