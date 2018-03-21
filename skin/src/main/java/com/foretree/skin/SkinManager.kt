package com.foretree.skin

import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import java.lang.Exception


/**
 * Created by silen on 20/03/2018.
 */

@SuppressLint("StaticFieldLeak")
object SkinManager {
    private lateinit var mApp: Application

    fun init(application: Application) {
        mApp = application
        mApp.registerActivityLifecycleCallbacks(SkinActivityLifecycle(ActivityLifecycleDelegateImpl()))
    }

    fun loadSkin(path: String) {
        if (path.isEmpty()) {
            SkinResources.getInstance(mApp.applicationContext).reset()
            SkinSharePreference.getInstance(mApp.applicationContext).setSkin("")
        } else {
            try {
                val assetManager = AssetManager::class.java.newInstance()
                val method = assetManager.javaClass.getMethod("addAssetPath", String::class.java)
                method.isAccessible = true
                method.invoke(assetManager, path)

                val resources = mApp.resources
                val skinResources = Resources(assetManager,resources.displayMetrics, resources.configuration)

                val packageName = mApp.packageManager.getPackageArchiveInfo(path,
                        PackageManager.GET_ACTIVITIES).packageName
                SkinResources.getInstance(mApp.applicationContext).applySkin(skinResources,
                        packageName)
                SkinSharePreference.getInstance(mApp.applicationContext).setSkin(path)

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}