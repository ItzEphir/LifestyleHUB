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
fun SignInScreen(viewModel: AuthViewModel = koinViewModel(), onSuccessfulSignIn: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(
                8.dp, alignment = Alignment.CenterVertically,
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val state by viewModel.signingState.collectAsStateWithLifecycle()
            var isSignInSuccessful by remember {
                mutableStateOf(true)
            }
            
            TextField(
                value = state.login,
                onValueChange = {
                    viewModel.changeLogin(it)
                    isSignInSuccessful = true
                },
                isError = isSignInSuccessful,
            )
            
            TextField(
                value = state.password,
                onValueChange = {
                    viewModel.changePassword(it)
                    isSignInSuccessful = true
                },
                isError = isSignInSuccessful,
            )
            
            Button(onClick = {
                viewModel.signIn(
                    onOk = onSuccessfulSignIn,
                    onError = {
                        isSignInSuccessful = false
                    },
                )
            }) {
                Text(
                    text = stringResource(id = com.ephirium.lifestylehub.androidBase.R.string.sign_in),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
        }
    }
}