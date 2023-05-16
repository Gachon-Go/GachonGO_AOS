package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class LoginResponseResult(
    @SerializedName("id") val id: Int,
    @SerializedName("jwt") val jwt: String,
)
