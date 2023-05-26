package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class MyPageResponseResult(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("point") val point: Int,
    @SerializedName("orderNum") val orderNum: Int,
    @SerializedName("deliveryNum") val deliveryNum: Int,
    @SerializedName("postNum") val postNum: Int,
)
