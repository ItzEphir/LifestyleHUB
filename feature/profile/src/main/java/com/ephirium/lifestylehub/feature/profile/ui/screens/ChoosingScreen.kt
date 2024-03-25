package com.ephirium.lifestylehub.feature.profile.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.lifestylehub.feature.profile.presentation.viewmodel.AuthViewModel
import com.ephirium.lifestylehub.feature.profile.ui.components.Profile
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChoosingScreen(
    viewModel: AuthViewModel = koinViewModel(),
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    
    val user by viewModel.userState.collectAsStateWithLifecycle()
    
    LaunchedEffect(key1 = user) {
        if(user == null){
            viewModel.loadUser()
        }
    }
    
    when(user){
        null -> Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onSignInClick) {
                    Text(
                        text = stringResource(id = com.ephirium.lifestylehub.androidBase.R.string.sign_in),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
                Button(onSignUpClick) {
                    Text(
                        text = stringResource(id = com.ephirium.lifestylehub.androidBase.R.string.sign_up),
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
        }
        else -> Profile(user!!, modifier = Modifier.fillMaxSize())
    }
    
    
}