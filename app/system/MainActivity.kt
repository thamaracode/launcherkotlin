package com.launcher

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.launcher.ui.AppDrawer
import com.launcher.viewmodel.LauncherViewModel

class MainActivity : ComponentActivity() {
    
    // Instantiate the ViewModel
    private val viewModel: LauncherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Modern way to handle back presses and prevent exiting the launcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Do nothing, or later intercept to close the app drawer
            }
        })
        
        setContent {
            MaterialTheme {
                val apps by viewModel.installedApps.collectAsState()

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black) // Dark theme base
                ) {
                    // Currently showing the AppDrawer directly for testing.
                    AppDrawer(apps = apps)
                }
            }
        }
    }
}