package com.example.smashnews.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.smashnews.core.data.repository.AppRepository

class MainViewModel(private val repo: AppRepository) : ViewModel() {

    fun getCategory() = repo.getCategory().asLiveData()

}