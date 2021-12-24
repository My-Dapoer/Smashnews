package com.example.smashnews.core.data.source.remote.response

import com.example.smashnews.core.data.source.remote.model.Berita
import com.example.smashnews.core.data.source.remote.model.Category
import com.example.smashnews.core.data.source.remote.model.Response

data class Data(
    val categories: List<Category> = emptyList(),
    val articles: List<Berita> = emptyList(),
    val tags: List<Category> = emptyList(),
    val article: Berita = Berita(),
    val responses: List<Response> = emptyList(),
    val static: Berita = Berita(),
)