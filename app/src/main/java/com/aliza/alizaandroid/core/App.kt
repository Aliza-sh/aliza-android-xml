package com.aliza.alizaandroid.core

import com.aliza.alizaandroid.di.component.DaggerMainComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerMainComponent
            .builder()
            .application(this)
            .build()
    }

}