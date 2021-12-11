package com.example.smashnews.core.data.repository

import com.example.smashnews.core.data.source.local.LocalDataSource
import com.example.smashnews.core.data.source.remote.RemoteDataSource
import com.example.smashnews.core.data.source.remote.network.Resource
import com.inyongtisto.myhelper.extension.getErrorBody
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {

    fun getCategory() = flow {
        emit(Resource.loading(null))
        try {
            remote.getCategory().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    emit(Resource.success(body?.data?.categories))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }

    fun getBeritaByCategory(id: String?) = flow {
        emit(Resource.loading(null))
        try {
            remote.getBeritaByCategory(id).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    emit(Resource.success(body?.data?.articles))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message ?: "Default error dongs", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "Terjadi Kesalahan", null))
        }
    }
}