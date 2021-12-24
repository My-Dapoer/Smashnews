package com.example.smashnews.core.data.source.remote

import com.example.smashnews.core.data.source.remote.network.ApiService
import com.example.smashnews.core.data.source.remote.request.ResponseRequest

class RemoteDataSource(private val api: ApiService) {

    suspend fun getCategory() = api.getCategory()

    suspend fun getBeritaByCategory(id: String?) = api.getBeritaByCategory(id)

    suspend fun getBeritaBySubCategory(id: String?) = api.getBeritaBySubCategory(id)

    suspend fun getDetailBerita(id: String?) = api.getDetailBerita(id)

    suspend fun getTags() = api.getTags()

    suspend fun getBeritaByTag(id: String?) = api.getBeritaByTag(id)

    suspend fun postResponse(data: ResponseRequest?) = api.postResponse(data)

    suspend fun getInfo() = api.getInfo()

}