package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.databinding.FragmentAnimationFromXmlBinding

class FragmentAnimationFromXml : Fragment() {
    lateinit var binding: FragmentAnimationFromXmlBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimationFromXmlBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnAnim.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(context, R.anim.animation_ball);

            binding.imgAnim.startAnimation(animation)
        }
    }
}