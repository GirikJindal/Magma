package com.magma.app.data.models

data class User(
    val id: String,
    val name: String,
    var points: Int = 0,
    val roles: MutableList<String> = mutableListOf()
)
