package com.example.mycommercejetpack.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycommercejetpack.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val message: String? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun register(email: String, password: String) {
        _authState.value = AuthState(isLoading = true)
        repository.registerWithEmail(email, password) { success, msg ->
            _authState.value = AuthState(isSuccess = success, message = msg)
        }
    }

    fun login(email: String, password: String) {
        _authState.value = AuthState(isLoading = true)
        viewModelScope.launch {
            val result = repository.loginWithEmailSecond(email, password)
            result.onSuccess {
                _authState.value = AuthState(isSuccess = true, message = "logined Successfully")
                println("Login success, user saved: ${it.email}")
            }.onFailure {
                println("Login failed: ${it.message}")
            }
        }

//        repository.loginWithEmail(email, password) { success, msg ->
//            _authState.value = AuthState(isSuccess = success, message = msg)
//        }
    }
}