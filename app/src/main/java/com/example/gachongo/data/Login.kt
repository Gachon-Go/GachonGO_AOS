package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("fcmId") val fcmId: String,
    @SerializedName("provider") val provider: String,
    @SerializedName("token") val token: String,
)
