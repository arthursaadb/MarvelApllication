package com.example.marvelapplication.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
): Parcelable