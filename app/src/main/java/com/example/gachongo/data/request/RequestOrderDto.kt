package com.example.gachongo.data.request

import com.google.gson.annotations.SerializedName

data class RequestOrderDto(
    @SerializedName("content")
    val content: String,
    @SerializedName("estimatedTime")
    val estimatedTime: String,
    @SerializedName("title")
    val title: String
)