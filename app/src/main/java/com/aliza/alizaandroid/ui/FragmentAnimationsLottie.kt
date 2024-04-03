package com.aliza.alizaandroid.ui

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import com.aliza.alizaandroid.databinding.FragmentAnimationsLottieBinding
import com.aliza.alizaandroid.databinding.FragmentViewAnimationsBinding
import com.aliza.alizaandroid.ext.BaseFragment

class FragmentAnimationsLottie : BaseFragment<FragmentAnimationsLottieBinding>(
    FragmentAnimationsLottieBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnAnim.setOnClickListener {
            binding.animLottie.playAnimation()
        }

    }
}