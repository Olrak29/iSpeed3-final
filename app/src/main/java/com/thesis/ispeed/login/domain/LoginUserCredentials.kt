package com.thesis.ispeed.login.domain

import com.thesis.ispeed.login.data.LoginRepository
import com.thesis.ispeed.app.shared.data.UserDetails
import javax.inject.Inject

class LoginUserCredentials @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(userDetails: UserDetails) = loginRepository.loginCredentials(userDetails)
}