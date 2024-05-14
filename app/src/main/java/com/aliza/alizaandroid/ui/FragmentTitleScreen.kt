package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentTitleScreenBinding

class FragmentTitleScreen :
    BaseFragment<FragmentTitleScreenBinding>(FragmentTitleScreenBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.playBtn.setOnClickListener {
            navigate(R.id.action_titleScreen_to_register, true)
        }

        binding.leaderboardBtn.setOnClickListener {
            navigate(R.id.action_titleScreen_to_navigation_leaderboard, true)
        }

    }

}
