package com.aliza.alizaandroid.utils

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Context.showSnackbar(view: View, str: String): Snackbar {
    return Snackbar.make(view, str, Snackbar.LENGTH_LONG)
}