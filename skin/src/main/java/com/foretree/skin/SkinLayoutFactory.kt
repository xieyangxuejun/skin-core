package com.foretree.skin

import android.content.Context
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View

/**
 * Created by silen on 20/03/2018.
 */
class SkinLayoutFactory: LayoutInflater.Factory2 {
    private val mAttrList = arrayOf(
            "android.widget.",
            "android.view.",
            "android.webkit."
    )

    override fun onCreateView(parent: View?, name: String?, context: Context?, attrs: AttributeSet?): View {
        
    }

    fun createViewFromName(name: String?, context: Context?, attrs: AttributeSet?): View{
        
    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View = null!!
}