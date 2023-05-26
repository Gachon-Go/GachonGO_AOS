package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class LoginResponseResult(
    @SerializedName("id") val id: Int,
    @SerializedName("jwt") val jwt: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("point") val point: Int,
    @SerializedName("profileImage") val profileImage: String,
)
