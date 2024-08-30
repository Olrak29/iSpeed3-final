package com.thesis.ispeed.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thesis.ispeed.app.shared.data.UserDetails
import com.thesis.ispeed.app.shared.domain.FirebaseUseCase
import com.thesis.ispeed.app.shared.extension.coRunCatching
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val firebaseUseCase: FirebaseUseCase
) : ViewModel() {

    private val _userDetails = MutableLiveData<UserDetails>()

    val userDetails: LiveData<UserDetails> get() = _userDetails

    init {
        getUserDetails()
    }

    private fun getUserDetails() {
        viewModelScope.launch {
            coRunCatching {
                firebaseUseCase.getUserDetails()
            }.onSuccess {
                _userDetails.value = it
            }.onFailure {
                _userDetails.value = null
            }
        }
    }

    fun logoutUser() = firebaseUseCase.logoutUser()
}