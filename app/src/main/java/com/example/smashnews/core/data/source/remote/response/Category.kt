package com.example.smashnews.core.data.source.remote.response

data class Category(
    val created_at: String? = null,
    val description: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val slug: String? = null,
    val sub_categories: List<SubCategory> = emptyList(),
    val updated_at: String? = null
)