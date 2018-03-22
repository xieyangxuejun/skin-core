package com.foretree.skin

import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.text.TextUtils
import java.lang.Exception
import java.util.*


/**
 * Created by silen on 20/03/2018.
 */

@SuppressLint("StaticFieldLeak")
object SkinManager : Observable() {
    private lateinit var mApp: Application

    fun init(application: Application) {
        mApp = application
        SkinResources.init(mApp.applicationContext)
        SkinSharePreference.init(mApp.applicationContext)
        mApp.registerActivityLifecycleCallbacks(SkinActivityLifecycle(ActivityLifecycleDelegateImpl()))
        loadSkin(SkinSharePreference.getInstance().getSkin())
    }

    fun loadSkin(path: String?) {
        if (TextUtils.isEmpty(path)) {
            SkinResources.getInstance().reset()
            SkinSharePreference.getInstance().setSkin("")
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
                SkinResources.getInstance().applySkin(skinResources,
                        packageName)
                SkinSharePreference.getInstance().setSkin(path!!)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        setChanged()
        notifyObservers()

    }
}