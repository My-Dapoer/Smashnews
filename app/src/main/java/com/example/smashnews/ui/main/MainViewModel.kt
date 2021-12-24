package com.example.smashnews.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.smashnews.core.data.repository.AppRepository
import com.example.smashnews.core.data.source.remote.request.ResponseRequest

class MainViewModel(private val repo: AppRepository) : ViewModel() {

    fun getCategory() = repo.getCategory().asLiveData()

    fun getBeritaByCategory(id: String?) = repo.getBeritaByCategory(id).asLiveData()

    fun getBeritaBySubCategory(id: String?) = repo.getBeritaBySubCategory(id).asLiveData()

    fun getDetailBerita(id: String?) = repo.getDetailBerita(id).asLiveData()

    fun getTags() = repo.getTags().asLiveData()

    fun getBeritaByTag(id: String?) = repo.getBeritaByTag(id).asLiveData()

    fun postResponse(data: ResponseRequest?) = repo.postResponse(data).asLiveData()
}