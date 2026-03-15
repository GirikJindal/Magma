package com.magma.app.data.models

data class Mentorship(
    val id: String,
    val mentorId: String,
    val menteeId: String,
    val topic: String,
    val active: Boolean = true
)
