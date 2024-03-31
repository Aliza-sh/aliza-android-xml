package com.aliza.alizaandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.fragment.app.Fragment
import com.aliza.alizaandroid.databinding.FragmentViewAnimationsBinding

class FragmentViewAnimations : Fragment() {

    lateinit var binding: FragmentViewAnimationsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewAnimationsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnAnimAlpha.setOnClickListener {
            alphaAnimation()
        }

        binding.radiogroupScale.setOnCheckedChangeListener { _, i ->

            when (i) {
                R.id.radio_pivot -> {
                    binding.btnAnimScale.setOnClickListener {
                        scaleAnimation(true)
                    }
                }

                R.id.radio_but_pivot -> {
                    binding.btnAnimScale.setOnClickListener {
                        scaleAnimation(false)
                    }
                }

            }
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
        binding.imgAnimAlpha.startAnimation(alphaAnimation)
    }

    lateinit var scaleAnimation: ScaleAnimation
    private fun scaleAnimation(pivot: Boolean) {
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
                    scaleAnimation.duration = 2000
                    scaleAnimation.fillAfter = true
                    binding.imgAnimScale.startAnimation(scaleAnimation)
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }
        binding.imgAnimScale.startAnimation(scaleAnimation)
    }
}