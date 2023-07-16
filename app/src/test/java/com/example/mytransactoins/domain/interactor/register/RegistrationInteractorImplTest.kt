package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.model.Result
import com.example.mytransactoins.domain.repo.UserRepo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class RegistrationInteractorImplTest {
    @Mock
    private lateinit var userRepo: UserRepo

    private lateinit var registrationInteractor: RegistrationInteractorImpl

    @Before
    fun setup() {
        registrationInteractor = RegistrationInteractorImpl(userRepo)
    }

    @Test
    fun `test registration is successful`() {
        `when`(userRepo.addUser(anyString(), anyString())).thenReturn(Result.Success(Unit))
        val result = registrationInteractor.registerUser("email", "password")
        assert(result is Result.Success)
    }

}