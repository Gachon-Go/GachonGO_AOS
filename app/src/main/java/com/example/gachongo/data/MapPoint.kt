package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class MapPoint(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
)
