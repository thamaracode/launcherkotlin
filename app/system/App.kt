package com.launcher.domain.model

import android.content.Intent
import android.graphics.drawable.Drawable

data class App(
    val name: String,
    val packageName: String,
    val icon: Drawable,
    val launchIntent: Intent?
)