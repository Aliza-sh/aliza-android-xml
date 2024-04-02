package com.aliza.alizaandroid.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.BounceInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.databinding.FragmentAnimationSetBinding
import com.google.android.material.card.MaterialCardView


class FragmentAnimationSet : Fragment() {

    lateinit var binding: FragmentAnimationSetBinding
    lateinit var animationSet: AnimationSet

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimationSetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        animationSet = AnimationSet(true)

        openChooseWindow()

        onCheckBoxClicked()

        binding.btnAnim.setOnClickListener {
            binding.imgAnim.startAnimation(animationSet)
        }
    }

    private fun openChooseWindow() {
        var open = false
        binding.btnOpenChooseWindow.setOnClickListener {
            if (open) {
                val translateX = 0f
                setAnimatorX(translateX,binding.CardViewChooseWindow)
                setAnimatorX(translateX,binding.btnOpenChooseWindow)
                binding.imgAnimChooseWindow.setImageResource(R.drawable.ic_right)
                open = false
            }
            else {
                val translateX = 620f
                setAnimatorX(translateX,binding.CardViewChooseWindow)
                setAnimatorX(translateX,binding.btnOpenChooseWindow)
                binding.imgAnimChooseWindow.setImageResource(R.drawable.ic_left)
                open = true
            }
        }
    }

    private fun setAnimatorX(translateX: Float, view: MaterialCardView,){
        val translateAnimation = ObjectAnimator.ofFloat(view, "translationX", translateX)
        translateAnimation.duration = 1000
        translateAnimation.start()
    }

    private fun onCheckBoxClicked() {
        binding.checkAlphaAnimation.setOnCheckedChangeListener { _, b ->
            if (b) {
                alphaAnimation()
            } else {}
        }
        binding.checkScaleAnimation.setOnCheckedChangeListener { _, b ->
            if (b) {
                scaleAnimation()
            } else {}
        }
        binding.checkTranslateAnimation.setOnCheckedChangeListener { _, b ->
            if (b) {
                translateAnimation()
            } else {}
        }
        binding.checkRotateAnimation.setOnCheckedChangeListener { _, b ->
            if (b) {
                rotateAnimation()
            } else {}
        }
    }

    private fun alphaAnimation() {
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
        animationSet.addAnimation(alphaAnimation)
    }

    private fun scaleAnimation() {
        var scaleAnimation: ScaleAnimation? = null
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
            animationSet.addAnimation(scaleAnimation)
    }

    private fun translateAnimation() {
        val translateAnimation = TranslateAnimation(
            0f, 500f,
            0f, 500f
        )
        translateAnimation.duration = 2000
        translateAnimation.interpolator = BounceInterpolator()
        translateAnimation.fillAfter = true
        animationSet.addAnimation(translateAnimation)
    }

    private fun rotateAnimation() {
        val rotateAnimation = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f,
        )
        rotateAnimation.duration = 2000
        rotateAnimation.fillAfter = true
        rotateAnimation.repeatCount = 5
        animationSet.addAnimation(rotateAnimation)
    }
}