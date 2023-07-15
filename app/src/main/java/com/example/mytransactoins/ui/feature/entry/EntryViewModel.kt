package com.example.mytransactoins.ui.feature.entry

import androidx.lifecycle.ViewModel
import com.example.mytransactoins.domain.interactor.entry.EntryInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val entryInteractor: EntryInteractor
) : ViewModel() {
    fun isLoggedIn(): Boolean {
        return entryInteractor.isLoggedIn()
    }
}