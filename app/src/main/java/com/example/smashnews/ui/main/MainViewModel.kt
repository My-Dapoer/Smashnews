package com.example.smashnews.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.smashnews.core.data.repository.AppRepository

class MainViewModel(private val repo: AppRepository) : ViewModel() {

    fun getCategory() = repo.getCategory().asLiveData()

    fun getBeritaByCategory(id: String?) = repo.getBeritaByCategory(id).asLiveData()

    fun getBeritaBySubCategory(id: String?) = repo.getBeritaBySubCategory(id).asLiveData()

    fun getDetailBerita(id: String?) = repo.getDetailBerita(id).asLiveData()
}