package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentExoPlayerBinding


class FragmentExoPlayer : BaseFragment<FragmentExoPlayerBinding>(
    FragmentExoPlayerBinding::inflate
) {

    lateinit var exoPlayer: ExoPlayer
    private val videoUri =
        "https://dunijet.ir/video/barname%20nevis%20ha%20az%20koja%20prozhe%20migiran.mp4"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        exoPlayer = ExoPlayer.Builder(requireContext()).build()

        // Build the media item.
        val mediaItem = MediaItem.fromUri(videoUri)
        // Set the media item to be played.
        exoPlayer.setMediaItem(mediaItem)
        // Prepare the player.
        exoPlayer.prepare()
        // It will be played as soon as it is prepared.
        exoPlayer.playWhenReady = true
        // Start the playback.
        exoPlayer.play()

        binding.PlayerViewMain.player = exoPlayer

    }

    override fun onDestroyView() {
        exoPlayer.release()
        super.onDestroyView()
    }

}