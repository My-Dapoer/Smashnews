package com.example.smashnews.core.data.source.remote

import com.example.smashnews.core.data.source.remote.network.ApiService

class RemoteDataSource(private val api: ApiService) {

    suspend fun getCategory() = api.getCategory()

    suspend fun getBeritaByCategory(id: String?) = api.getBeritaByCategory(id)

}