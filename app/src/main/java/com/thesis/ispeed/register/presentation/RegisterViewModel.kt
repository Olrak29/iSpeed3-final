package com.thesis.ispeed.register.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesis.ispeed.app.shared.data.UserDetails
import com.thesis.ispeed.app.shared.extension.coRunCatching
import com.thesis.ispeed.register.domain.ICreateUserCredential
import com.thesis.ispeed.register.domain.IRegisterCredential
import com.thesis.ispeed.register.domain.ISaveDetailsFireStore
import com.thesis.ispeed.register.domain.ISendEmailVerification
import com.thesis.ispeed.register.domain.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _registerState = MutableLiveData<RegisterState>(ShowRegisterNoData)

    val registerState: LiveData<RegisterState> = _registerState

    fun registerCredentials(userDetails: UserDetails) {
        viewModelScope.launch {
            updateUIState(state = ShowRegisterLoading)

            // This chainCall function execute a step by step network call.
            registerUseCase.apply {
                chainCall(
                    { registerCredentials(userDetails = userDetails) },
                    { sendEmailVerification() },
                    { saveFireStoreDetails(details = userDetails) }
                )
            }

            updateUIState(state = ShowRegisterDismissLoading)
        }
    }

    private suspend fun<T : IRegisterCredential> chainCall(vararg calls: (suspend () -> T)) {
        run chain@ {
            calls.onEachIndexed { _, codeToExecute ->
                coRunCatching {
                    codeToExecute.invoke()
                }.onSuccess { result ->
                    if (result.isNetworkSuccess()) handleChainCallback(result)
                }.onFailure {
                    updateUIState(state = ShowRegisterError(it))
                    return@chain
                }
            }
        }
    }

    private fun updateUIState(state: RegisterState) {
        _registerState.value = state
    }

    private fun handleChainCallback(result: Any) {
        when(result) {
            is ICreateUserCredential -> updateUIState(state = RegisterCredentialSuccess(result.authResult))
            is ISendEmailVerification -> updateUIState(state = EmailVerificationSuccess)
            is ISaveDetailsFireStore -> updateUIState(state = SaveFireStoreDetailsSuccess)
        }
    }
}