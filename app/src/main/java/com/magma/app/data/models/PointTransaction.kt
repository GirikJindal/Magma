package com.magma.app.data.models

data class PointTransaction(
    val reason: String,
    val amount: Int,
    val timestamp: Long = System.currentTimeMillis()
)
