package com.foretree.skin

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import java.lang.Exception
import java.lang.reflect.Constructor
import java.util.*

/**
 * Created by silen on 20/03/2018.
 */
class SkinLayoutFactory(private var activity: Activity) : LayoutInflater.Factory2, Observer {
    private val skinAttribute: SkinAttribute = SkinAttribute()

    override fun update(o: Observable?, arg: Any?) {
        skinAttribute.applySkin()
    }

    private val mClassPrefixList = arrayOf(
            "android.widget.",
            "android.view.",
            "android.webkit."
    )

    private var mConstructorMap: HashMap<String, Constructor<out View>> = HashMap()


    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? = null

    override fun onCreateView(parent: View?, name: String?, context: Context?, attrs: AttributeSet?): View? {
        //android view
        var view = createViewFromName(name!!, context, attrs)
        if (view == null) {
            view = createView(name, context, attrs)
        }
        //add attr in view
        skinAttribute.load(view, attrs)
        return view
    }

    /**
     * create view
     */
    private fun createViewFromName(name: String, context: Context?, attrs: AttributeSet?): View? {
        var view: View? = null
        mClassPrefixList.forEach {
            view = createView(it + name, context, attrs)
            if (view != null) return view
        }
        return view
    }

    /**
     * iterator create veiw
     */
    private fun createView(name: String, context: Context?, attrs: AttributeSet?): View? {
        var constructor = mConstructorMap[name]
        try {
            if (constructor == null) {
                val clazz = context?.classLoader?.loadClass(name)?.asSubclass(View::class.java)
                constructor = clazz?.getConstructor(Context::class.java, AttributeSet::class.java)
                mConstructorMap[name] = constructor!!
            }
        } catch (e: Exception) {
        }
        return constructor?.newInstance(context, attrs)
    }
}