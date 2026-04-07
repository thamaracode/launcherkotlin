package com.launcher.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.launcher.domain.model.App
import com.launcher.system.AppManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LauncherViewModel(application: Application) : AndroidViewModel(application) {

    private val appManager = AppManager(application)

    private val _installedApps = MutableStateFlow<List<App>>(emptyList())
    val installedApps: StateFlow<List<App>> = _installedApps.asStateFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            _installedApps.value = appManager.getInstalledApps()
        }
    }
}