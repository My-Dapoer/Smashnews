package com.example.smashnews.core.data.source.remote.model

data class Berita(
    val author: String? = null,
    val categoryable_id: String? = null,
    val categoryable_type: String? = null,
    val created_at: Any? = null,
    var description: String? = null,
    val id: Int? = null,
    val image: String? = null,
    val name: String? = null,
    val publish_at: String? = null,
    val slug: String? = null,
    val updated_at: Any? = null,
    var isSubCategory: Boolean = false,
    var slugCategory: String? = null,
)