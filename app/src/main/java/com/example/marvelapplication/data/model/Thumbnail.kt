package com.example.marvelapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
    val extension: String,
    val path: String
): Parcelable {
    fun getPoster(): String? =
        "${path}.${extension}"
}