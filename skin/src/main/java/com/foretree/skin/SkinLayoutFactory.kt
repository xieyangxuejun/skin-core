package com.foretree.skin

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import java.lang.Exception
import java.lang.reflect.Constructor

/**
 * Created by silen on 20/03/2018.
 */
class SkinLayoutFactory(private var activity: Activity,
                        private val skinAttribute: SkinAttribute = SkinAttribute()) : LayoutInflater.Factory2 {

    private val mClassPrefixList = arrayOf(
            "android.widget.",
            "android.view.",
            "android.webkit."
    )

    private var mConstructorMap: HashMap<String, Constructor<out View>> = HashMap()


    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? = null

    override fun onCreateView(parent: View?, name: String?, context: Context?, attrs: AttributeSet?): View {
        //android view
        val view = createViewFromName(name!!, context, attrs)

        //add attr in view
        skinAttribute.loadAttr(context, view, attrs)
        return view!!
    }

    /**
     * create view
     */
    private fun createViewFromName(name: String, context: Context?, attrs: AttributeSet?): View? {
        var view:View? = null
        mClassPrefixList.forEach {
            view = createView(name, context, attrs)
            if (view != null) return@forEach
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