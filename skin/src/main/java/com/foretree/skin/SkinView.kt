package com.foretree.skin

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

    fun applySkin() {
        var skinDrawable: SkinDrawable? = null
        skinPairs.forEach {
            when (AttrType.valueOf(it.attributeName)) {
                AttrType.background -> {
                    SkinResources
                            .getInstance()
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
                            .getInstance()
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
                            .getInstance()
                            .getColor(it.resId)
                            .let {
                                (view as TextView).setTextColor(it)
                            }
                }
                AttrType.drawableLeft -> {
                    SkinResources
                            .getInstance()
                            .getDrawable(it.resId)
                            .let {
                                skinDrawable = SkinDrawable(it, null, null, null)
                            }
                }
                AttrType.drawableRight -> {
                    SkinResources
                            .getInstance()
                            .getDrawable(it.resId)
                            .let {
                                skinDrawable = SkinDrawable(null, it, null, null)
                            }

                }
                AttrType.drawableTop -> {
                    SkinResources
                            .getInstance()
                            .getDrawable(it.resId)
                            .let {
                                skinDrawable = SkinDrawable(null, null, it, null)
                            }

                }
                AttrType.drawableBottom -> {
                    SkinResources
                            .getInstance()
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