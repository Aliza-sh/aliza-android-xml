package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentGameOverBinding

class FragmentGameOver : BaseFragment<FragmentGameOverBinding>(FragmentGameOverBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.playBtn4.setOnClickListener {
            navigate(R.id.action_gameOver_to_match,true,R.id.match,true)
        }

    }
}
