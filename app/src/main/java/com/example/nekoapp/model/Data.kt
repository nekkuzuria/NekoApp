package com.example.nekoapp.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("artist_href")
    val artistHref: String,

    @SerializedName("artist_name")
    val artistName: String,

    @SerializedName("url")
    val url: String
)
