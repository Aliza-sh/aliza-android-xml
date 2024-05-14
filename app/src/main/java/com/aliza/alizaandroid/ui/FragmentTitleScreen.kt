package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentTitleScreenBinding
import com.aliza.alizaandroid.utils.navOptions

class FragmentTitleScreen :
    BaseFragment<FragmentTitleScreenBinding>(FragmentTitleScreenBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.playBtn.setOnClickListener {
            findNavController().navigate(R.id.action_titleScreen_to_register,null, navOptions( true))
        }

        binding.leaderboardBtn.setOnClickListener {
            findNavController().navigate(R.id.action_titleScreen_to_navigation_leaderboard, null, navOptions( true))
        }

    }

}
