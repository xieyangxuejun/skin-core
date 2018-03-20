package com.foretree.skin

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater

/**
 * Created by silen on 20/03/2018.
 */
class ActivityLifecycleDelegateImpl: ActivityLifecycleDelegate {
    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        val layoutInflater = LayoutInflater.from(activity)
        try {
            val field = layoutInflater.javaClass.getDeclaredField("mFactorySet")
            field.isAccessible = true
            field.setBoolean(layoutInflater, false)
        }catch (e: Exception) {}



    }

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }
}