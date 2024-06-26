package com.ephirium.lifestylehub.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.ephirium.lifestylehub.androidBase.navigation.ScreenProvider
import com.ephirium.lifestylehub.ui.components.MainScaffold
import com.ephirium.lifestylehub.ui.theme.LifestyleHUBTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {
    
    private val screenProvider by inject<ScreenProvider>()
    
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
            ), 0
        )
        
        setContent {
            KoinAndroidContext {
                LifestyleHUBTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MainScaffold(screenProvider = screenProvider)
                    }
                }
            }
        }
    }
}