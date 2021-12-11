package com.example.smashnews.core.data.source.remote.model

data class Berita(
    val author: String,
    val categoryable_id: String,
    val categoryable_type: String,
    val created_at: Any,
    val description: String,
    val id: Int,
    val image: String,
    val name: String,
    val publish_at: String,
    val slug: String,
    val updated_at: Any
)