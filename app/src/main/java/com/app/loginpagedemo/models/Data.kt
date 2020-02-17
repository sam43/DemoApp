package com.app.loginpagedemo.models


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("body")
    val resBody: String? = null,
    @SerializedName("id")
    val resId: Int? = null,
    @SerializedName("title")
    val resTitle: String? = null,
    @SerializedName("userId")
    val resUserId: Int? = null
)