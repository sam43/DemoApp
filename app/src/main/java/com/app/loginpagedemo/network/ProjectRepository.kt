package com.app.loginpagedemo.network

import androidx.lifecycle.LiveData
import com.app.loginpagedemo.models.Data
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProjectRepository {
    private var service: ApiService

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        val intercept = httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(intercept)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        service = retrofit.create(ApiService::class.java)
    }

    fun getAllPosts(): LiveData<List<Data>> {
        val apiObject = ApiObject()
        service.getPosts().enqueue(apiObject.getApiObject())
        return apiObject.getData()
    }
}