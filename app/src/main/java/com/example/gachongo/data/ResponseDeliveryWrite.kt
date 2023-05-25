package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class ResponseDeliveryWrite(
    @SerializedName("code")
    val code: Int,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: String,
)
