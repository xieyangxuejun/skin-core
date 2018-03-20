package com.foretree.skin

import android.annotation.SuppressLint
import android.app.Application

/**
 * Created by silen on 20/03/2018.
 */

@SuppressLint("StaticFieldLeak")
object SkinManager {
    private var mApp: Application? = null

    fun init(application: Application) {
        mApp = application
        mApp?.registerActivityLifecycleCallbacks(SkinActivityLifecycle(ActivityLifecycleDelegateImpl()))
    }
}