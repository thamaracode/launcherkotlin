package com.launcher

import android.os.Bundle
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
    
    // Prevent the back button from exiting the launcher
    override fun onBackPressed() {
        // Do nothing, or close the app drawer if it's open
    }
}