package com.dto

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int? = null,
    val firstName: String,
    val lastName: String
)
