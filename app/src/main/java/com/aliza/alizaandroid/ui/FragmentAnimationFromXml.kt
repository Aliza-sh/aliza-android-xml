package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.databinding.FragmentAnimationFromXmlBinding
import com.aliza.alizaandroid.base.BaseFragment

class FragmentAnimationFromXml : BaseFragment<FragmentAnimationFromXmlBinding>(
    FragmentAnimationFromXmlBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnAnim.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(context, R.anim.animation_ball);

            binding.imgAnim.startAnimation(animation)
        }
    }
}