package com.aliza.alizaandroid.ui

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.OptIn
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentExoPlayerCustomBinding


class FragmentExoPlayerCustom : BaseFragment<FragmentExoPlayerCustomBinding>(
    FragmentExoPlayerCustomBinding::inflate
) {

    companion object {
        private const val URL = "https://d1gnaphp93fop2.cloudfront.net/videos/multiresolution/rendition_new10.m3u8"
        private var isFullScreen = false
        private var isLock = false
        private const val INCREMENT_MILLIS = 5000L
    }

    private var playbackPosition = 0L

    lateinit var exoPlayer: ExoPlayer
    lateinit var imageViewFullScreen: ImageView
    private lateinit var imageViewLock: ImageView
    private lateinit var linearLayoutControlUp: LinearLayout
    private lateinit var linearLayoutControlBottom: LinearLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setFindViewById(requireActivity())

        preparePlayer(requireActivity())
        setLockScreen(requireActivity())
        setFullScreen(requireActivity())

    }

    private fun setFindViewById(requireActivity: FragmentActivity) {
        imageViewFullScreen = requireActivity.findViewById(R.id.imageViewFullScreen)
        imageViewLock = requireActivity.findViewById(R.id.imageViewLock)
        linearLayoutControlUp = requireActivity.findViewById(R.id.linearLayoutControlUp)
        linearLayoutControlBottom = requireActivity.findViewById(R.id.linearLayoutControlBottom)
    }

    @OptIn(UnstableApi::class) private fun preparePlayer(requireActivity: FragmentActivity) {
        exoPlayer = ExoPlayer
            .Builder(requireActivity)
            .setSeekBackIncrementMs(INCREMENT_MILLIS)
            .setSeekForwardIncrementMs(INCREMENT_MILLIS)
            .build()
        exoPlayer.playWhenReady = true
        binding.PlayerViewMain.player = exoPlayer
        val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()
        val mediaItem =
            MediaItem.fromUri(URL)
        val mediaSource =
            HlsMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(mediaItem)
        exoPlayer?.apply {
            setMediaSource(mediaSource)
            seekTo(playbackPosition)
            playWhenReady = playWhenReady
            prepare()
        }
    }


    private fun lockScreen(lock: Boolean) {
        if (lock) {
            linearLayoutControlUp.visibility = View.INVISIBLE
            linearLayoutControlBottom.visibility = View.INVISIBLE
        } else {
            linearLayoutControlUp.visibility = View.VISIBLE
            linearLayoutControlBottom.visibility = View.VISIBLE
        }
    }

    private fun setLockScreen(requireActivity: FragmentActivity) {
        imageViewLock.setOnClickListener {
            if (!isLock) {
                imageViewLock.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity,
                        R.drawable.ic_lock
                    )
                )
            } else {
                imageViewLock.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity,
                        R.drawable.ic_lock_open
                    )
                )
            }
            isLock = !isLock
            lockScreen(isLock)
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun setFullScreen(requireActivity: FragmentActivity) {
        setIconScreen(requireActivity)
        imageViewFullScreen.setOnClickListener {
            if (!isFullScreen) {
                imageViewFullScreen.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity,
                        R.drawable.ic_fullscreen_exit
                    )
                )
                requireActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            } else {
                imageViewFullScreen.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity,
                        R.drawable.ic_fullscreen
                    )
                )
                requireActivity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            isFullScreen = !isFullScreen
        }
    }

    private inline fun setIconScreen(requireActivity: FragmentActivity) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            imageViewFullScreen.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity,
                    R.drawable.ic_fullscreen_exit
                )
            )
        }else{
            imageViewFullScreen.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity,
                    R.drawable.ic_fullscreen
                )
            )
        }
    }

    /*@Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (isLock) return
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageViewFullScreen.performClick()
        } else super.onBackPressed()
    }*/

    override fun onStop() {
        super.onStop()
        exoPlayer?.stop()
    }
    override fun onDestroyView() {
        exoPlayer.release()
        super.onDestroyView()
    }

    override fun onPause() {
        super.onPause()
        exoPlayer?.pause()
    }

}