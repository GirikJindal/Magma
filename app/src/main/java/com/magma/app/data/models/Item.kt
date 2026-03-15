package com.magma.app.data.models

data class Item(
    val id: String,
    val sellerId: String,
    val title: String,
    val description: String,
    val priceCents: Int = 0,
    var available: Boolean = true
)
