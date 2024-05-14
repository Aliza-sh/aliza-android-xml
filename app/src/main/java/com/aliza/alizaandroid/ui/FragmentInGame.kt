package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentInGameBinding

class FragmentInGame : BaseFragment<FragmentInGameBinding>(FragmentInGameBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val gameOverListener: (View) -> Unit = {
            navigate(R.id.action_inGame_to_gameOver,true,R.id.match)
        }
        binding.checkBox.setOnClickListener(gameOverListener)
        binding.checkBox2.setOnClickListener(gameOverListener)
        binding.checkBox4.setOnClickListener(gameOverListener)

        binding.checkBox3.setOnClickListener {
            navigate(R.id.action_inGame_to_resultsWinner,true,R.id.match)
        }

    }
}
