package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentVideoViewBinding

class FragmentVideoView : BaseFragment<FragmentVideoViewBinding>(
    FragmentVideoViewBinding::inflate
) {
    private val url = "https://dunijet.ir/video/barname%20nevis%20ha%20az%20koja%20prozhe%20migiran.mp4"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.videoViewMain.setVideoPath(url)

        binding.videoViewMain.setOnPreparedListener {
            binding.videoViewMain.start()

            val mediaController = MediaController(requireActivity())
            mediaController.setMediaPlayer( binding.videoViewMain )
            binding.videoViewMain.setMediaController( mediaController )
            mediaController.setAnchorView( binding.videoViewMain )
        }

        binding.button.setOnClickListener {
            binding.videoViewMain.pause()
        }
    }

    override fun onDestroyView() {
        binding.videoViewMain.stopPlayback()
        super.onDestroyView()
    }

    private fun otherFun(){
        binding.videoViewMain.duration
        binding.videoViewMain.currentPosition
        binding.videoViewMain.start()
        binding.videoViewMain.pause()
        binding.videoViewMain.seekTo(5000)
        binding.videoViewMain.setOnCompletionListener {
        }
        binding.videoViewMain.setOnErrorListener { mp, what, extra ->
            true
        }
    }
}