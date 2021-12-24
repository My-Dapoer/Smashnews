package com.example.smashnews.util

import com.chibatching.kotpref.KotprefModel
import com.example.smashnews.core.data.source.remote.model.DataResponse
import com.inyongtisto.myhelper.extension.toJson
import com.inyongtisto.myhelper.extension.toModel

object Prefs : KotprefModel() {

    var response by nullableStringPref(null)

    fun saveResponse(slug: String?) {
        if (slug == null) return
        val res = ArrayList<String>()
        res.addAll(getResponse())
        if (!res.any { it == slug }) {
            res.add(slug)
            response = DataResponse(res).toJson()
        }
    }

    fun getResponse(): List<String> {
        if (response.isNullOrEmpty()) return emptyList()
        val data = response.toModel(DataResponse::class.java) ?: DataResponse()
        return data.responses
    }

    fun isThereResponse(slug: String) = getResponse().any { it == slug }
}