package com.magma.app.data.models

data class LibraryResource(
    val id: String,
    val title: String,
    val authors: List<String> = emptyList(),
    val url: String? = null,
    val resourceType: String = "document"
)
