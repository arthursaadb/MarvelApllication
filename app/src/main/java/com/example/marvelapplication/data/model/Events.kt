package com.example.marvelapplication.data.model

data class Events(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)