package com.example.mytransactoins.domain.interactor.register

import com.example.mytransactoins.domain.repo.UserRepo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
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
        val email = "email"
        assert(registrationInteractor.registerUser(email, "password").isSuccessful)
    }

}