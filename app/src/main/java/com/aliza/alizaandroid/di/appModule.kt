package com.aliza.alizaandroid.di

import com.aliza.alizaandroid.di.module.NetModule
import com.aliza.alizaandroid.di.module.imageModule.GlideLoader
import com.aliza.alizaandroid.di.module.imageModule.ImageLoaderService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single <ImageLoaderService>{ GlideLoader(androidContext()) }

    single { NetModule().createApiService() }

}