package com.thesis.ispeed.register.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.thesis.ispeed.register.data.dto.RegisterCredentialResponse
import com.thesis.ispeed.register.data.dto.SaveDetailsFireStore
import com.thesis.ispeed.register.data.dto.SendEmailVerificationResponse
import com.thesis.ispeed.app.shared.data.UserDetails
import com.thesis.ispeed.app.util.Default.Companion.DB_USER
import com.thesis.ispeed.register.domain.ICreateUserCredential
import com.thesis.ispeed.register.domain.ISaveDetailsFireStore
import com.thesis.ispeed.register.domain.ISendEmailVerification
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) {

    suspend fun registerCredentials(userDetails: UserDetails) : ICreateUserCredential {
        val authResult = firebaseAuth.createUserWithEmailAndPassword(
            userDetails.email.orEmpty(),
            userDetails.password.orEmpty()
        ).await()
        return RegisterCredentialResponse(authResult)
    }

    suspend fun sendEmailVerification() : ISendEmailVerification {
        val emailVerificationTask = firebaseAuth.currentUser?.sendEmailVerification()
        emailVerificationTask?.await()
        return SendEmailVerificationResponse(emailVerificationTask?.isSuccessful == true)
    }

    suspend fun saveFireStoreDetails(details: UserDetails) : ISaveDetailsFireStore {
        val userId = firebaseAuth.currentUser?.uid ?: error("userId failed to generate")
        val saveDetailsStatus = fireStore.collection(DB_USER).document(userId).set(details)
        saveDetailsStatus.await()
        return SaveDetailsFireStore(saveDetailsStatus.isSuccessful)
    }
}