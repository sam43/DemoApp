package com.app.loginpagedemo.network

import com.app.loginpagedemo.models.Data
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts") // base: https://jsonplaceholder.typicode.com/
    fun getPosts(): Call<List<Data>>
}
