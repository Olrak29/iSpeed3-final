package com.imrkjoseph.ispeed.login.domain

import com.imrkjoseph.ispeed.login.data.LoginRepository
import com.imrkjoseph.ispeed.app.shared.data.UserDetails
import javax.inject.Inject

class LoginUserCredentials @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(userDetails: UserDetails) = loginRepository.loginCredentials(userDetails)
}