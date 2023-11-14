package com.example.nekoapp.model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("results")
    val results: List<Data>
)