package com.foretree.skin

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * <p>
 *     activity life cycle
 *     </p>
 * Created by silen on 20/03/2018.
 */
class SkinActivityLifecycle(private val mDelegate: ActivityLifecycleDelegate) : Application.ActivityLifecycleCallbacks {

    override fun onActivityPaused(activity: Activity?) {
        mDelegate.onActivityPaused(activity)
    }

    override fun onActivityResumed(activity: Activity?) {
        mDelegate.onActivityResumed(activity)
    }

    override fun onActivityStarted(activity: Activity?) {
        mDelegate.onActivityStarted(activity)
    }

    override fun onActivityDestroyed(activity: Activity?) {
        mDelegate.onActivityDestroyed(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        mDelegate.onActivitySaveInstanceState(activity, outState)
    }

    override fun onActivityStopped(activity: Activity?) {
        mDelegate.onActivityStopped(activity)
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        mDelegate.onActivityCreated(activity, savedInstanceState)
    }
}