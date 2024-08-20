package com.imrkjoseph.ispeed.register.data.dto

import com.google.firebase.auth.AuthResult
import com.imrkjoseph.ispeed.register.domain.ICreateUserCredential
import com.imrkjoseph.ispeed.register.domain.ISaveDetailsFireStore
import com.imrkjoseph.ispeed.register.domain.ISendEmailVerification

data class RegisterCredentialResponse(val auth: AuthResult) : ICreateUserCredential {
    override val authResult: AuthResult
        get() = auth
}

data class SendEmailVerificationResponse(val isVerified: Boolean) : ISendEmailVerification {
    override val isEmailVerified: Boolean
        get() = isVerified
}

data class SaveDetailsFireStore(val isSavedSuccess: Boolean) : ISaveDetailsFireStore {
    override val isSaveSuccess: Boolean
        get() = isSavedSuccess
}