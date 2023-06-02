package com.example.gachongo.data.request

import com.google.gson.annotations.SerializedName

data class RequestCommentDto(
    @SerializedName("content")
    val content: String
)