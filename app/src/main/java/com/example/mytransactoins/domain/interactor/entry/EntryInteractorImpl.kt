package com.example.mytransactoins.domain.interactor.entry

import com.example.mytransactoins.domain.repo.UserRepo
import javax.inject.Inject

class EntryInteractorImpl @Inject constructor(
    private val userRepo: UserRepo
) : EntryInteractor {
    override fun isLoggedIn(): Boolean {
        return userRepo.isLoggedIn()
    }
}