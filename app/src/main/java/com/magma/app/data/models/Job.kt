package com.magma.app.data.models

data class Job(
    val id: String,
    val company: String,
    val role: String,
    val description: String,
    val stipendCents: Int = 0,
    val remote: Boolean = true
)
