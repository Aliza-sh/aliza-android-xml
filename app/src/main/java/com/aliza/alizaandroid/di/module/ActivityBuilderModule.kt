package com.aliza.alizaandroid.di.module

import com.aliza.alizaandroid.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity?
}