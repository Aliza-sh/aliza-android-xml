package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentInGameBinding
import com.aliza.alizaandroid.utils.navOptions

class FragmentInGame : BaseFragment<FragmentInGameBinding>(FragmentInGameBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val gameOverListener: (View) -> Unit = {
            findNavController().navigate(R.id.action_inGame_to_gameOver,null, navOptions(true,R.id.match) )
        }
        binding.checkBox.setOnClickListener(gameOverListener)
        binding.checkBox2.setOnClickListener(gameOverListener)
        binding.checkBox4.setOnClickListener(gameOverListener)

        binding.checkBox3.setOnClickListener {
            findNavController().navigate(R.id.action_inGame_to_resultsWinner,null, navOptions(true,R.id.match))
        }

    }
}
