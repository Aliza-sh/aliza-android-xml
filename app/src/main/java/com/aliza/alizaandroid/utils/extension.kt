package com.aliza.alizaandroid.utils

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun Context.showSnackbar(view: View, str: String): Snackbar {
    return Snackbar.make(view, str, Snackbar.LENGTH_LONG)
}

fun <T : Any> Single<T>.asyncRequest() :Single<T> {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.asyncRequest() : Completable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}