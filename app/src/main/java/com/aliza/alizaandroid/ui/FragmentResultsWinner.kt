package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentResultsWinnerBinding
import com.aliza.alizaandroid.utils.navOptions

class FragmentResultsWinner :
    BaseFragment<FragmentResultsWinnerBinding>(FragmentResultsWinnerBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.playBtn2.setOnClickListener {
            findNavController().navigate(R.id.action_resultsWinner_to_match, null, navOptions(true, R.id.match, true))
        }

        binding.leaderboardBtn2.setOnClickListener {
            findNavController().navigate(R.id.action_resultsWinner_to_navigation_leaderboard,null, navOptions(true))
        }
    }
}