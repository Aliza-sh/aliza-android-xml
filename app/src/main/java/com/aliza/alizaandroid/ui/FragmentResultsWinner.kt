package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentResultsWinnerBinding

class FragmentResultsWinner :
    BaseFragment<FragmentResultsWinnerBinding>(FragmentResultsWinnerBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.playBtn2.setOnClickListener {
            navigate(R.id.action_resultsWinner_to_match, true, R.id.match, true)
        }

        binding.leaderboardBtn2.setOnClickListener {
            navigate(R.id.action_resultsWinner_to_navigation_leaderboard, true)
        }
    }
}