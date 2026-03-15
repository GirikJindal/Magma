package com.magma.app.data.models

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val organizerId: String,
    val timestamp: Long,
    val location: String? = null
)
