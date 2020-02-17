package com.app.loginpagedemo.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.loginpagedemo.models.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiObject {
    private val TAG = "APP"
    private val data = MutableLiveData<List<Data>>()
    private val apiObject = object : Callback<List<Data>> {
        override fun onFailure(call: Call<List<Data>>, t: Throwable) {
            Log.d(TAG, t.message.toString())
        }

        override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
            handle(call, response)
        }

    }


    fun getApiObject(): Callback<List<Data>> {
        return apiObject
    }

    fun getData(): LiveData<List<Data>> {
        return data
    }

    private fun handle(call: Call<List<Data>>, response: Response<List<Data>>) {
        Log.d(TAG, "request: ${call.request()}")
        when(response.code()) {
            200 -> {
                Log.i(TAG, "Success")
            }
            else -> {
                Log.e(TAG, "Failed")
            }
        }
    }
}