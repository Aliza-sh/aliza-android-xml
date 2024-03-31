package com.aliza.alizaandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import com.aliza.alizaandroid.databinding.FragmentAnimationSetBinding

class FragmentAnimationSet : Fragment() {

lateinit var binding: FragmentAnimationSetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationSetBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



    }

    private fun alphaAnimation(): AlphaAnimation {
        val alphaAnimation = AlphaAnimation(1f, 0f)
        alphaAnimation.duration = 1000
        alphaAnimation.fillAfter = true
        alphaAnimation.repeatCount = 10
        alphaAnimation.repeatMode = Animation.REVERSE
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
        })
        return alphaAnimation
    }

    private fun scaleAnimation(pivot: Boolean): ScaleAnimation {
        var scaleAnimation: ScaleAnimation? = null

        if (pivot) {
            scaleAnimation = ScaleAnimation(
                0f, 1f,
                0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
            )
            scaleAnimation.duration = 2000
            scaleAnimation.fillAfter = true
            scaleAnimation.repeatCount = 5
            scaleAnimation.repeatMode = Animation.REVERSE
        } else {
            scaleAnimation = ScaleAnimation(
                0f, 1f,
                0f, 1f
            )
            scaleAnimation.duration = 2000
        }
        return scaleAnimation
    }

    private fun translateAnimation(): TranslateAnimation {
        val translateAnimation = TranslateAnimation(
            0f, 500f,
            0f, 500f
        )
        translateAnimation.duration = 2000
        translateAnimation.interpolator = BounceInterpolator()
        translateAnimation.fillAfter = true
        return translateAnimation
    }

    private fun rotateAnimation(pivot: Boolean): RotateAnimation {
        var rotateAnimation: RotateAnimation? = null

        if (pivot) {
            rotateAnimation = RotateAnimation(
                0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
            )
            rotateAnimation.duration = 2000
            rotateAnimation.fillAfter = true
            rotateAnimation.repeatCount = 5
        } else {
            rotateAnimation = RotateAnimation(
                0f, 360f,
                0f, 1f
            )
            rotateAnimation.duration = 2000
        }
        return rotateAnimation
    }
}