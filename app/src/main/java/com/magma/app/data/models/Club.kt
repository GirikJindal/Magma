package com.magma.app.data.models

data class Club(
    val id: String,
    val name: String,
    var points: Int = 0,
    val responsibilities: MutableList<String> = mutableListOf()
)
