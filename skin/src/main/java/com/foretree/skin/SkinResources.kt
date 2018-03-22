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
    private lateinit var mSkinPackageName: String
    private var defaultSkin = true

    companion object {
        private lateinit var mInstance: SkinResources

        fun getInstance(): SkinResources = mInstance

        fun init(context: Context) {
            mInstance = SkinResources(context)
        }
    }

    fun applySkin(resources: Resources, packageName: String) {
        defaultSkin = false
        this.mSkinResources = resources
        this.mSkinPackageName = packageName
    }

    fun getBackground(resId: Int): Any {
        mAppResources.getResourceTypeName(resId)
                .let {
                    return if (it.contains("color")) {
                        getColor(resId)
                    } else {
                        getDrawable(resId)
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

    fun getColor(resId: Int): Int {
        val skinId: Int = getIdentifierId(resId)
        if (skinId == 0 || defaultSkin) return mAppResources.getColor(skinId)
        return mSkinResources.getColor(skinId)
    }

    private fun getIdentifierId(resId: Int): Int {
        if (defaultSkin) return resId

        val entryName = mAppResources.getResourceEntryName(resId)
        val typeName = mAppResources.getResourceTypeName(resId)
        return mSkinResources.getIdentifier(entryName, typeName, mSkinPackageName)
    }

    fun reset() {
        defaultSkin = true
        mSkinPackageName = ""
    }

}