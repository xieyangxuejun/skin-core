package com.foretree.skin

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView

/**
 * <p>
 *     Skin View Container List of Attrs
 *     </p>
 */
class SkinView(var view: View, var skinPairs: ArrayList<SkinPair>) {

    fun applySkin(context: Context?) {
        if (context == null) return
        var skinDrawable: SkinDrawable? = null
        skinPairs.forEach {
            when (AttrType.valueOf(it.attributeName)) {
                AttrType.background -> {
                    SkinResources
                            .getInstance(context)
                            .getBackground(it.resId)
                            .let {
                                if (it is Int) {
                                    view.setBackgroundColor(it)
                                } else {
                                    view.background = it as Drawable
                                }
                            }
                }
                AttrType.src -> {
                    SkinResources
                            .getInstance(context)
                            .getBackground(it.resId)
                            .let {
                                if (view !is ImageView) return
                                if (it is Int) {
                                    (view as ImageView).setImageDrawable(ColorDrawable(it))
                                } else if (it is Drawable) {
                                    (view as ImageView).setImageDrawable(it)
                                }
                            }
                }
                AttrType.textColor -> {
                    SkinResources
                            .getInstance(context)
                            .getColorStateList(resId = it.resId)
                            .let {
                                (view as TextView).setTextColor(it)
                            }
                }
                AttrType.drawableLeft -> {
                    SkinResources
                            .getInstance(context)
                            .getDrawable(it.resId)
                            .let {
                                skinDrawable = SkinDrawable(it, null, null, null)
                            }
                }
                AttrType.drawableRight -> {
                    SkinResources
                            .getInstance(context)
                            .getDrawable(it.resId)
                            .let {
                                skinDrawable = SkinDrawable(null, it, null, null)
                            }

                }
                AttrType.drawableTop -> {
                    SkinResources
                            .getInstance(context)
                            .getDrawable(it.resId)
                            .let {
                                skinDrawable = SkinDrawable(null, null, it, null)
                            }

                }
                AttrType.drawableBottom -> {
                    SkinResources
                            .getInstance(context)
                            .getDrawable(it.resId)
                            .let {
                                skinDrawable = SkinDrawable(null, null, null, it)
                            }

                }
                else -> {

                }
            }
        }

        if (skinDrawable != null && view is TextView) {
            (view as TextView).setCompoundDrawables(skinDrawable?.dLeft,skinDrawable?.dRight, skinDrawable?.dTop,
                    skinDrawable?.dBottom)
        }
    }
}