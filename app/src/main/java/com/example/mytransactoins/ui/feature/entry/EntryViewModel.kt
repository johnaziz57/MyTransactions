package com.example.mytransactoins.ui.feature.entry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytransactoins.domain.interactor.entry.EntryInteractor
import com.example.mytransactoins.ui.feature.mode.LiveDataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val entryInteractor: EntryInteractor
) : ViewModel() {
    val isLoggedInLiveData: LiveData<LiveDataResult<Boolean>>
        get() = _isLoggedIn

    private val _isLoggedIn = MutableLiveData<LiveDataResult<Boolean>>()
    fun isLoggedIn() {
        _isLoggedIn.value = LiveDataResult(isSuccessful = true, data = entryInteractor.isLoggedIn())
    }
}