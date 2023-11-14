package com.example.retrofitapp.network

import com.example.nekoapp.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("neko?amount=20")
    fun getAllUser(): Call<Users>

}