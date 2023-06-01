package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class ChargePoint(
    @SerializedName("price") val price: Int,
)
