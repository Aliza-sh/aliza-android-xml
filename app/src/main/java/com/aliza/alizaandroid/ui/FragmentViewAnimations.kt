package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.databinding.FragmentViewAnimationsBinding
import com.aliza.alizaandroid.base.BaseFragment

class FragmentViewAnimations : BaseFragment<FragmentViewAnimationsBinding>(
    FragmentViewAnimationsBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnAnimAlpha.setOnClickListener {
            binding.imgAnimAlpha.startAnimation(alphaAnimation())
        }

        binding.radiogroupScale.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.radio_pivot -> {
                    binding.btnAnimScale.setOnClickListener {
                        binding.imgAnimScale.startAnimation(scaleAnimation(true))
                    }
                }

                R.id.radio_but_pivot -> {
                    binding.btnAnimScale.setOnClickListener {
                        binding.imgAnimScale.startAnimation(scaleAnimation(false))
                    }
                }
            }
        }

        binding.btnAnimTranslate.setOnClickListener {
            binding.imgAnimTranslate.startAnimation(translateAnimation())
        }

        binding.radiogroupRotate.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.radio_rotate_pivot -> {
                    binding.btnAnimRotate.setOnClickListener {
                        binding.imgAnimRotate.startAnimation(rotateAnimation(true))
                    }
                }

                R.id.radio_rotate_but_pivot -> {
                    binding.btnAnimRotate.setOnClickListener {
                        binding.imgAnimRotate.startAnimation(rotateAnimation(false))
                    }
                }
            }
        }

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
            scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
                    scaleAnimation = ScaleAnimation(
                        1f, -0f,
                        1f, 1f
                    )
                    scaleAnimation!!.duration = 2000
                    scaleAnimation!!.fillAfter = true
                    binding.imgAnimScale.startAnimation(scaleAnimation)
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }
        return scaleAnimation as ScaleAnimation
    }

    private fun translateAnimation(): TranslateAnimation {
        val translateAnimation = TranslateAnimation(
            0f, 500f,
            0f, 500f
        )
        translateAnimation.duration = 2000
        translateAnimation.interpolator = BounceInterpolator()
        translateAnimation.fillAfter = true
        translateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                val translateAnimation = TranslateAnimation(
                    500f, 00f,
                    500f, 1000f,
                )
                translateAnimation.duration = 2000
                translateAnimation.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}
                    override fun onAnimationEnd(animation: Animation?) {
                        val translateAnimation = TranslateAnimation(
                            0f, 0f,
                            1000f, 0f
                        )
                        translateAnimation.duration = 2000
                        translateAnimation.fillAfter = true
                        binding.imgAnimTranslate.startAnimation(translateAnimation)
                    }

                    override fun onAnimationRepeat(animation: Animation?) {}
                })
                binding.imgAnimTranslate.startAnimation(translateAnimation)
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
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