package com.ephirium.lifestylehub.feature.profile.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.lifestylehub.common.ResponseResult
import com.ephirium.lifestylehub.common.on
import com.ephirium.lifestylehub.common.onOk
import com.ephirium.lifestylehub.domain.usecases.GetCurrentUserUseCase
import com.ephirium.lifestylehub.domain.usecases.SignInUseCase
import com.ephirium.lifestylehub.domain.usecases.SignUpUseCase
import com.ephirium.lifestylehub.feature.profile.presentation.mapper.toUi
import com.ephirium.lifestylehub.feature.profile.presentation.model.SigningUiModel
import com.ephirium.lifestylehub.feature.profile.presentation.model.UserUiModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.Duration.Companion.seconds

class AuthViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val signIn: SignInUseCase,
    private val signUp: SignUpUseCase,
    private val getCurrentUser: GetCurrentUserUseCase
) : ViewModel() {
    
    val signingState: StateFlow<SigningUiModel> =
        savedStateHandle.getStateFlow(SIGNING_STATE_KEY, SigningUiModel("", ""))
    
    val userState: StateFlow<UserUiModel?> = savedStateHandle.getStateFlow(USER, null)
    
    fun changeLogin(login: String) {
        savedStateHandle[SIGNING_STATE_KEY] = signingState.value.copy(
            login = login
        )
    }
    
    fun changePassword(password: String) {
        savedStateHandle[SIGNING_STATE_KEY] = signingState.value.copy(
            password = password
        )
    }
    
    @OptIn(FlowPreview::class)
    fun loadUser(){
        viewModelScope.launch {
            getCurrentUser().timeout(5.seconds).catch {
                if(it is TimeoutCancellationException){
                    currentCoroutineContext().cancel(it)
                }
            }.collectLatest {
                savedStateHandle[USER] = it?.toUi()
            }
        }
    }
    
    fun signIn(onOk: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            signIn(signingState.value.login, signingState.value.password).collectLatest { responseResult ->
                responseResult.onOk { user ->
                    savedStateHandle[USER] = user.toUi()
                    onOk()
                }.on<ResponseResult.Error> {
                    onError()
                }
            }
        }
    }
    
    fun signUp(onOk: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            signUp(signingState.value.login, signingState.value.password).collectLatest { responseResult ->
                responseResult.onOk { user ->
                    savedStateHandle[USER] = user.toUi()
                    onOk()
                }.on<ResponseResult.Error> {
                    onError()
                }.on<ResponseResult.TimeoutError> {
                    onError()
                }.on<ResponseResult.HttpResponse> {
                    onError()
                }
            }
        }
    }
    
    companion object {
        private const val SIGNING_STATE_KEY = "signing_state"
        private const val USER = "user"
    }
}