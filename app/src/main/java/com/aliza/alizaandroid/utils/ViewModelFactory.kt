package com.aliza.alizaandroid.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aliza.alizaandroid.model.repository.MainRepository
import com.aliza.alizaandroid.ui.student.StudentViewModel

class StudentViewModelFactory(private val mainRepository: MainRepository) :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudentViewModel(mainRepository) as T
    }

}