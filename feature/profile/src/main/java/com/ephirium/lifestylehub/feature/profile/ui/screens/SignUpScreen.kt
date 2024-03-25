package com.ephirium.lifestylehub.feature.profile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.lifestylehub.feature.profile.presentation.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(viewModel: AuthViewModel = koinViewModel(), onSuccessfulSignUp: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(
                8.dp, alignment = Alignment.CenterVertically,
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val state by viewModel.signingState.collectAsStateWithLifecycle()
            
            var isSuccessfulSignUp by remember {
                mutableStateOf(true)
            }
            
            TextField(value = state.login, onValueChange = {
                viewModel.changeLogin(it)
                isSuccessfulSignUp = true
            })
            
            TextField(value = state.password, onValueChange = {
                viewModel.changePassword(it)
                isSuccessfulSignUp = true
            })
            
            Button(onClick = {
                viewModel.signUp(
                    onOk = onSuccessfulSignUp,
                    onError = {
                        isSuccessfulSignUp = false
                    },
                )
            }) {
                Text(
                    text = stringResource(id = com.ephirium.lifestylehub.androidBase.R.string.sign_up),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}