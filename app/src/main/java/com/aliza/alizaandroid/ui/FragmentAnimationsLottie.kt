package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import com.aliza.alizaandroid.databinding.FragmentAnimationsLottieBinding
import com.aliza.alizaandroid.base.BaseFragment

class FragmentAnimationsLottie : BaseFragment<FragmentAnimationsLottieBinding>(
    FragmentAnimationsLottieBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnAnim.setOnClickListener {
            binding.animLottie.playAnimation()
        }

    }
}