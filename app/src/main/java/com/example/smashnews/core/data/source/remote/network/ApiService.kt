package com.example.smashnews.core.data.source.remote.network

import com.example.smashnews.core.data.source.remote.response.MainResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("categories")
    suspend fun getCategory(): Response<MainResponse>
//
//    // "https://127.0.0.1:8000/api/register"
//    @POST("register")
//    suspend fun register(
//        @Body data: RegisterRequest
//    ): Response<LoginResponse>
//
//    @PUT("update-user/{id}")
//    suspend fun updateUser(
//        @Path("id") int: Int,
//        @Body data: UpdateProfileRequest
//    ): Response<LoginResponse>
//
//    @Multipart
//    @POST("upload-user/{id}")
//    suspend fun uploadUser(
//        @Path("id") int: Int? = null,
//        @Part data: MultipartBody.Part? = null
//    ): Response<LoginResponse>
}