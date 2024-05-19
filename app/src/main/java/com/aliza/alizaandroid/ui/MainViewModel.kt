package com.aliza.alizaandroid.ui

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel() : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter : StateFlow<Int> get() = _counter

    fun incrementCounter() {
        _counter.value++
    }

}