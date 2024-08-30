package com.thesis.ispeed.app.shared.domain

import com.thesis.ispeed.app.shared.data.repository.FirebaseRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class FirebaseUseCase @Inject constructor(
    private val repository: FirebaseRepository
) {

    suspend fun getUserDetails() = repository.getUserDetails()

    suspend fun forgotPassword(email: String) = repository.forgotPassword(email = email)

    fun logoutUser() = repository.logoutUser()
}