package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentGameOverBinding
import com.aliza.alizaandroid.utils.navOptions

class FragmentGameOver : BaseFragment<FragmentGameOverBinding>(FragmentGameOverBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.playBtn4.setOnClickListener {
            findNavController().navigate(R.id.action_gameOver_to_match ,null, navOptions(true,R.id.match,true))
        }

    }
}
