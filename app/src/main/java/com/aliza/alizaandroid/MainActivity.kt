package com.aliza.alizaandroid

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.google.android.material.slider.Slider
import java.util.Locale
import java.util.Timer
import java.util.TimerTask


class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var timer: Timer
    private var isPlaying = false
    private var isUserChanging = false
    private var isMute = false
    private var isLooping = false
    private var isAnimator = false
    private lateinit var animator: ObjectAnimator

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareMusic()

        binding.btnPlayPause.setOnClickListener { configureMusic() }
        binding.btnGoBefore.setOnClickListener { goBeforeMusic() }
        binding.btnGoAfter.setOnClickListener { goAfterMusic() }
        binding.btnVolumeOnOff.setOnClickListener { configureVolume() }
        binding.btnLoop.setOnClickListener { isLooping() }

        binding.sliderMain.addOnChangeListener { slider, value, fromUser ->
            binding.txtLeft.text = convertMillisToString(value.toLong())
            isUserChanging = fromUser
        }

        binding.sliderMain.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {

            @SuppressLint("RestrictedApi")
            override fun onStartTrackingTouch(slider: Slider) {

            }

            @SuppressLint("RestrictedApi")
            override fun onStopTrackingTouch(slider: Slider) {
                mediaPlayer.seekTo(slider.value.toInt())
            }

        })

        mediaPlayer.setOnCompletionListener {
            animator.pause()
            mediaPlayer.pause()
            binding.btnPlayPause.setImageResource(R.drawable.ic_play)
            isPlaying = false
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
        mediaPlayer.release()
    }

    private fun prepareMusic() {

        animator = ObjectAnimator.ofFloat(binding.frameCover, "rotation", 0f, 360f)
        animator.interpolator = LinearInterpolator()
        animator.repeatCount = Animation.INFINITE
        animator.duration = 15000
        isAnimator = true

        mediaPlayer = MediaPlayer.create(this, R.raw.ho3ein)

        binding.btnPlayPause.setImageResource(R.drawable.ic_play)

        binding.sliderMain.valueTo = mediaPlayer.duration.toFloat()

        binding.txtRight.text = convertMillisToString(mediaPlayer.duration.toLong())

        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    binding.sliderMain.value = mediaPlayer.currentPosition.toFloat()
                }
            }
        }, 1000, 1000)

    }

    private fun configureMusic() {

        if (isPlaying) {

            animator.pause()

            mediaPlayer.pause()
            binding.btnPlayPause.setImageResource(R.drawable.ic_play)
            isPlaying = false
        } else {

            if (isAnimator){
                animator.start()
                isAnimator = false
            }else{
                animator.resume()
            }

            mediaPlayer.start()
            binding.btnPlayPause.setImageResource(R.drawable.ic_pause)
            isPlaying = true
        }
    }

    private fun isLooping() {
        if (isLooping) {
            binding.btnLoop.setImageResource(R.drawable.ic_repeat)
            mediaPlayer.isLooping = false
            isLooping = false
        } else {
            binding.btnLoop.setImageResource(R.drawable.ic_repeat_one)
            mediaPlayer.isLooping = true
            isLooping = true
        }
    }

    @SuppressLint("InlinedApi")
    private fun configureVolume() {

        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager

        if (isMute) {
            audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI)
            binding.btnVolumeOnOff.setImageResource(R.drawable.ic_volume_on)
            isMute = false

        } else {
            audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI)
            binding.btnVolumeOnOff.setImageResource(R.drawable.ic_volume_off)
            isMute = true
        }

    }

    private fun goAfterMusic() {

        val now = mediaPlayer.currentPosition
        val newValue = now + 15000
        mediaPlayer.seekTo(newValue)

    }

    private fun goBeforeMusic() {

        val now = mediaPlayer.currentPosition
        val newValue = now - 15000
        mediaPlayer.seekTo(newValue)

    }

    private fun convertMillisToString(duration: Long): String {

        val second = duration / 1000 % 60
        val minute = duration / (1000 * 60) % 60

        return java.lang.String.format(Locale.US, "%02d:%02d", minute, second)
    }

}