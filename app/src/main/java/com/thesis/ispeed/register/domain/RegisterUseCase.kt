package com.thesis.ispeed.register.domain

import com.thesis.ispeed.register.data.repository.RegisterRepository
import com.thesis.ispeed.app.shared.data.UserDetails
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend fun registerCredentials(userDetails: UserDetails) = registerRepository.registerCredentials(userDetails)

    suspend fun sendEmailVerification() = registerRepository.sendEmailVerification()

    suspend fun saveFireStoreDetails(details: UserDetails) = registerRepository.saveFireStoreDetails(details)
}