package com.aliza.alizaandroid.di.module

import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ AppModule::class ] )
class ImageModule {

    @Provides
    @Singleton
    fun providePicasso() :Picasso {
        return Picasso.get()
    }

/*
    @Provides
    @Singleton
    fun provideGlide( context: Context ) {

    }
*/

}