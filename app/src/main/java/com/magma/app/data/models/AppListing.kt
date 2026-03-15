package com.magma.app.data.models

data class AppListing(
    val id: String,
    val developerId: String,
    val title: String,
    val description: String,
    val priceCents: Int = 0,
    val downloadUrl: String? = null
)
