package com.launcher.system

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.launcher.domain.model.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppManager(private val context: Context) {

    suspend fun getInstalledApps(): List<App> = withContext(Dispatchers.IO) {
        val packageManager = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }

        // Query all activities that are main entry points
        val resolveInfos = packageManager.queryIntentActivities(intent, 0)

        resolveInfos.map { resolveInfo ->
            val packageName = resolveInfo.activityInfo.packageName
            App(
                name = resolveInfo.loadLabel(packageManager).toString(),
                packageName = packageName,
                icon = resolveInfo.loadIcon(packageManager),
                launchIntent = packageManager.getLaunchIntentForPackage(packageName)
            )
        }.sortedBy { it.name.lowercase() } // Alphabetical sorting for the App Drawer
    }
}