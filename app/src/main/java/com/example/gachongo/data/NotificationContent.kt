package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class NotificationContent(
    @SerializedName("content") val content: String,
    @SerializedName("flag") val flag: Int,
)