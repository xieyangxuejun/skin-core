package com.foretree.skin

import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView

/**
 * Created by silen on 21/03/2018.
 */
class SkinAttribute {

    private val mAttributes = arrayOf(
            AttrType.src.name,
            AttrType.background.name,
            AttrType.textColor.name,
            AttrType.drawableLeft.name,
            AttrType.drawableRight.name,
            AttrType.drawableTop.name,
            AttrType.drawableBottom.name
    )

    private val mSkinViews = arrayListOf<SkinView>()

    fun load(view: View?, attrs: AttributeSet?) {
        val skinPairs = arrayListOf<SkinPair>()

        var index = 0
        val attributeCount = attrs?.attributeCount as Int
        while (index < attributeCount) {
            val attributeName = attrs.getAttributeName(index)
            if (mAttributes.contains(attributeName)) {
                val attributeValue: String = attrs.getAttributeValue(index)
                var resId = 0
                when {
                    attributeValue.startsWith("#") -> {

                    }
                    attributeValue.startsWith("?") -> {

                    }
                    else -> {
                        resId = attributeValue.substring(1).toInt()
                    }
                }
                if (resId != 0) {
                    val skinPair = SkinPair(attributeName, resId)
                    skinPairs.add(skinPair)
                }
            }
            index++
        }


        if (view != null && skinPairs.isNotEmpty() || view is TextView) {
            val skinView = SkinView(view, skinPairs)
            skinView.applySkin()
            mSkinViews.add(skinView)
            Log.d("skin==>", "add view => ${skinView::class.java}")
        }
    }

    fun applySkin() {
        mSkinViews.forEach {
            it.applySkin()
        }
    }
}
