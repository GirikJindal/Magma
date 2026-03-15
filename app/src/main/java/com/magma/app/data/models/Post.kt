package com.magma.app.data.models

data class Post(
    val id: String,
    val authorId: String,
    val title: String,
    val body: String,
    val timestamp: Long = System.currentTimeMillis(),
    var likes: Int = 0,
    var anonymous: Boolean = false
)
