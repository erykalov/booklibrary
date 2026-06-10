package com.example.booklibrary.models

import java.util.UUID

data class Book(
    val uuid: UUID = UUID.randomUUID(),
    val title: String,
    val author: String,
    val year: String,
    var rating: Float = 0.0f
)