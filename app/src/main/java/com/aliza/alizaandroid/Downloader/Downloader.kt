package com.aliza.alizaandroid.Downloader

interface Downloader {
    fun downloadFile(url:String):Long
    fun downloadStatus(downloadId:Long):String
}