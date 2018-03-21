package com.foretree.skin

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable

/**
 * Created by silen on 21/03/2018.
 */
class SkinResources private constructor(context: Context) {
    private lateinit var mSkinResources: Resources

    private var mAppResources: Resources = context.resources
    private lateinit var mSkinPacketName: String
    private var defaultSkin = true

    companion object {
        private lateinit var mInstance: SkinResources

        fun getInstance(context: Context): SkinResources {
            if (mInstance == null)
                mInstance = SkinResources(context)
            return mInstance
        }
    }

    fun getBackground(resId: Int): Any {
        mAppResources.getResourceTypeName(resId)
                .let {
                    if (it.contains("color")) {
                        return getColor(resId)
                    } else {
                        return getDrawable(resId)
                    }
                }

    }

    fun getColorStateList(resId: Int): ColorStateList {
        val skinId = getIdentifierId(resId)
        if (skinId == 0 || defaultSkin) return mAppResources.getColorStateList(resId)
        return mSkinResources.getColorStateList(resId)
    }

    fun getDrawable(resId: Int): Drawable {
        val skinId = getIdentifierId(resId)
        if (skinId == 0 || defaultSkin) return mAppResources.getDrawable(resId)
        return mSkinResources.getDrawable(skinId)
    }

    private fun getColor(resId: Int): Int {
        val skinId: Int = getIdentifierId(resId)
        if (skinId == 0 || defaultSkin) return mAppResources.getColor(skinId)
        return mSkinResources.getColor(skinId)
    }

    private fun getIdentifierId(resId: Int): Int {
        if (defaultSkin) return resId

        val entryName = mAppResources.getResourceEntryName(resId)
        val typeName = mAppResources.getResourceTypeName(resId)
        return mSkinResources.getIdentifier(entryName, typeName, mSkinPacketName)
    }

    fun reset() {
        defaultSkin = true
        mAppResources = null!!
        mSkinResources = null!!
        mSkinPacketName = ""
    }

}