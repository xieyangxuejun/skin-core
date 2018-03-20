package com.foretree.skin

import android.app.Activity
import android.os.Bundle

/**
 *  <p>
 *     activity life cycle delegate
 * Created by silen on 20/03/2018.
 */
interface ActivityLifecycleDelegate: LifecycleDelegate {
    fun onActivityPaused(activity: Activity?)
    fun onActivityResumed(activity: Activity?)
    fun onActivityStarted(activity: Activity?)
    fun onActivityDestroyed(activity: Activity?)
    fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?)
    fun onActivityStopped(activity: Activity?)
    fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?)
}