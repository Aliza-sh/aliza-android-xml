package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentLeaderboardBinding

class FragmentLeaderboard : BaseFragment<FragmentLeaderboardBinding>(FragmentLeaderboardBinding::inflate) , LeaderboardAdapter.LeaderboardEvents {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val viewAdapter = LeaderboardAdapter(arrayOf("Flo", "Ly", "Jo","Fl", "Lyo", "Jop","Flow", "Lyly", "Jojo","Flower", "Lela", "sep") , this)
        binding.leaderboardList.adapter = viewAdapter

    }
    override fun onItemClicked() {
        navigate(R.id.action_leaderboard_to_userProfile,true)
    }
}