package com.foretree.skin

import android.app.Activity
import android.os.Bundle
import android.support.v4.view.LayoutInflaterCompat
import android.view.LayoutInflater



/**
 * Created by silen on 20/03/2018.
 */
class ActivityLifecycleDelegateImpl: ActivityLifecycleDelegate {
    private val mLayoutFactoryMap = hashMapOf<Activity, SkinLayoutFactory>()

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        val layoutInflater = LayoutInflater.from(activity)
        try {
            val field = LayoutInflater::class.java.getDeclaredField("mFactorySet")
            field.isAccessible = true
            field.setBoolean(layoutInflater, false)
        }catch (e: Exception) {
            e.printStackTrace()
        }

        val skinLayoutFactory = SkinLayoutFactory(activity!!)
        LayoutInflaterCompat.setFactory2(layoutInflater, skinLayoutFactory)
        SkinManager.addObserver(skinLayoutFactory)
        mLayoutFactoryMap[activity] = skinLayoutFactory
    }

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
        val remove = mLayoutFactoryMap.remove(activity)
        SkinManager.deleteObserver(remove)
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    fun updateSkin(activity: Activity?) {
        val layoutFactory = mLayoutFactoryMap[activity]
        layoutFactory?.update(null, null)
    }
}