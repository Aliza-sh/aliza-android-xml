package com.aliza.alizaandroid.Downloader

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import okhttp3.OkHttpClient
import okhttp3.Request

class AndroidDownloader(
    private val context: Context
): Downloader {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)
    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType(fileType(url))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(url)
            .setDescription("Downloading...")
            .addRequestHeader("Authorization", "Bearer <token>")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, url.substringAfterLast("/"))
        return downloadManager.enqueue(request)
    }
    private fun fileType(url:String):String{
        val client = OkHttpClient()
        val requestMain = Request.Builder().url(url).build()
        val response = client.newCall(requestMain).execute()
        val mimeType = response.header("Content-Type")

        if (mimeType != null) {
            if (mimeType.startsWith("image/")) {
                // Handle image file
                Log.v("ooo",mimeType)
            } else if (mimeType.startsWith("video/")) {
                // Handle video file
                Log.v("ooo",mimeType)
            } else if (mimeType.startsWith("application/")) {
                // Handle application file
                Log.v("ooo",mimeType)
            } else {
                // Handle other file types
                Log.v("ooo",mimeType)
            }
        }
        return mimeType.toString()
    }

    override fun downloadStatus(downloadId: Long): String {

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val query = DownloadManager.Query().setFilterById(downloadId)
        val cursor: Cursor = downloadManager.query(query)
        if (cursor.moveToFirst()) {
            val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS) + 1)
            if (status == DownloadManager.STATUS_SUCCESSFUL) {
                // Download completed successfully
                return "Download completed!"
            } else if (status == DownloadManager.STATUS_FAILED) {
                // Download failed
                return "Download failed!"
            } else {
                // Download is still in progress
                return "Download is in progress..."
            }
        }
        cursor.close()
        return "Download failed!"
    }
}