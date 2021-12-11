package com.example.smashnews.core.data.source.remote.response

import com.example.smashnews.core.data.source.remote.model.Berita
import com.example.smashnews.core.data.source.remote.model.Category

data class Data(
    val categories: List<Category> = emptyList(),
    val articles: List<Berita> = emptyList(),
    val article: Berita = Berita()
)