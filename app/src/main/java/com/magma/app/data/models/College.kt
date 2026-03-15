package com.magma.app.data.models

data class College(
    val id: String,
    val name: String,
    val clubs: MutableList<Club> = mutableListOf(),
    var points: Int = 0,
    val members: MutableList<User> = mutableListOf(),
    val transactions: MutableList<PointTransaction> = mutableListOf()
)
