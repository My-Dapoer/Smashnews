package com.example.smashnews.core.data.source.remote.network

import com.example.smashnews.core.data.source.remote.request.ResponseRequest
import com.example.smashnews.core.data.source.remote.response.MainResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("categories")
    suspend fun getCategory(): Response<MainResponse>

    @GET("article/category/{id}")
    suspend fun getBeritaByCategory(
        @Path("id") int: String?
    ): Response<MainResponse>

    @GET("article/sub-category/{id}")
    suspend fun getBeritaBySubCategory(
        @Path("id") int: String?
    ): Response<MainResponse>

    @GET("article/show/{id}")
    suspend fun getDetailBerita(
        @Path("id") int: String?
    ): Response<MainResponse>

    @GET("tags")
    suspend fun getTags(): Response<MainResponse>

    @GET("article/tag/{id}")
    suspend fun getBeritaByTag(
        @Path("id") int: String?
    ): Response<MainResponse>

    @POST("submit-response")
    suspend fun postResponse(
        @Body body: ResponseRequest?
    ): Response<MainResponse>
}