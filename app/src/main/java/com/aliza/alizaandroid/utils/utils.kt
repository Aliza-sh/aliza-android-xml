package com.aliza.alizaandroid.utils

import androidx.navigation.NavOptions
import com.aliza.alizaandroid.R

fun navOptions(useCustomAnimation: Boolean = false,popUpToId: Int = -1,popUpToInclusive: Boolean = false):NavOptions {

    val navOptions = if (useCustomAnimation) {
        NavOptions.Builder()
            .setEnterAnim(R.anim.anim_slide_from_right)
            .setExitAnim(R.anim.anim_slide_to_left)
            .setPopEnterAnim(R.anim.anim_slide_from_left)
            .setPopExitAnim(R.anim.anim_slide_to_right)
            .setPopUpTo(popUpToId,popUpToInclusive)
            .build()
    } else {
        NavOptions.Builder()
            .build()
    }

    return navOptions
}