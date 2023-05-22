package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class OthersPositionResult(
    @SerializedName("postId") val postId: Int,
    @SerializedName("purpose") val purpose: String,
    @SerializedName("mapPoints") val mapPoints: ArrayList<MapPoint>,
)
