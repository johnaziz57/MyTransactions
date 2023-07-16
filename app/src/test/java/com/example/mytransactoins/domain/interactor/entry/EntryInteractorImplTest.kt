package com.example.mytransactoins.domain.interactor.entry

import com.example.mytransactoins.domain.model.User
import com.example.mytransactoins.domain.repo.UserRepo
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EntryInteractorImplTest {

    @Mock
    private lateinit var userRepo: UserRepo

    private lateinit var entryInteractor: EntryInteractorImpl

    @Before
    fun setup() {
        entryInteractor = Mockito.spy(EntryInteractorImpl(userRepo))
    }

    @Test
    fun `test is user logged in`() {
        `when`(userRepo.getCurrentUser()).thenReturn(User(""))
        assert(entryInteractor.isLoggedIn())
    }

    @Test
    fun `test user is not logged in`() {
        `when`(userRepo.getCurrentUser()).thenReturn(null)
        assertFalse(entryInteractor.isLoggedIn())
    }
}