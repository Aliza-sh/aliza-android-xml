package com.aliza.alizaandroid.di.component

import android.app.Application
import com.aliza.alizaandroid.core.App
import com.aliza.alizaandroid.di.module.ActivityBuilderModule
import com.aliza.alizaandroid.di.module.AppModule
import com.aliza.alizaandroid.di.module.ImageModule
import com.aliza.alizaandroid.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ActivityBuilderModule::class,
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ImageModule::class
    ]
)
interface MainComponent  : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MainComponent

    }

}