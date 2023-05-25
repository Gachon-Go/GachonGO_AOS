package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class RequestDeliveryWrite(
    @SerializedName("content")
    val content: String,
    @SerializedName("estimateTime")
    val estimatedTime: String,
    @SerializedName("title")
    val title: String,
)
