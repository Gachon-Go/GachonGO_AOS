package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class PointHistoryResponseResult(
    @SerializedName("time") val time: String,
    @SerializedName("content") val content: String,
    @SerializedName("point") val point: String,
)
