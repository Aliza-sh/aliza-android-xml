package com.aliza.alizaandroid

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.aliza.alizaandroid.Downloader.AndroidDownloader
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class DownloadThread(
    private val context: Context,
    private val binding: ActivityMainBinding
) : Thread() {
    private val handler = Handler(Looper.getMainLooper())

    override fun run() {
        super.run()
        val downloader = AndroidDownloader(context)
        val downloadId = downloader.downloadFile(binding.tieTilName.text.toString())
        handler.post {
            val snackbar = Snackbar.make(
                binding.root,
                downloader.downloadStatus(downloadId),
                Toast.LENGTH_SHORT
            )
            snackbar.show()
        }
    }
}