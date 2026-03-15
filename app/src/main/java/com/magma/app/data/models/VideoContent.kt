package com.magma.app.data.models

data class VideoContent(
    val id: String,
    val uploaderId: String,
    val title: String,
    val description: String,
    val priceCents: Int = 0,
    val url: String? = null
)
