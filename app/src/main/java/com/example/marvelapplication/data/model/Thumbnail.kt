package com.example.marvelapplication.data.model

data class Thumbnail(
    val extension: String,
    val path: String
) {
    fun getPoster(): String? =
        "${path}.${extension}"
}