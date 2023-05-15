package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("email") val email: String,
    @SerializedName("provider") val provider: String,
    @SerializedName("token") val token: String,
)
