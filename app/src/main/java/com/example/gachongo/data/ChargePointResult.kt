package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class ChargePointResult(
    @SerializedName("tid") val tid: String,
    @SerializedName("next_redirect_app_url") val next_redirect_app_url: String,
    @SerializedName("next_redirect_mobile_url") val next_redirect_mobile_url: String,
    @SerializedName("next_redirect_pc_url") val next_redirect_pc_url: String,
    @SerializedName("android_app_scheme") val android_app_scheme: String,
    @SerializedName("ios_app_scheme") val ios_app_scheme: String,
    @SerializedName("created_at") val created_at: String,
)
