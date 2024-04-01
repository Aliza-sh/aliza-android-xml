package com.aliza.alizaandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aliza.alizaandroid.databinding.FragmentAnimationsLottieBinding

class FragmentAnimationsLottie : Fragment() {

    lateinit var binding: FragmentAnimationsLottieBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationsLottieBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}