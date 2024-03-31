package com.aliza.alizaandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
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
}