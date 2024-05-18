package com.aliza.alizaandroid.di.module.imageModule

import android.widget.ImageView

interface ImageLoaderService {
    fun load(url: String, imageView: ImageView)
}