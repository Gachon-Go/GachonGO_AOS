package com.example.gachongo.data.response

import com.google.gson.annotations.SerializedName

data class ResponseDeliveryDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: MutableList<Result>,
) {
    data class Result(
        @SerializedName("commentNum")
        val commentNum: Int,
        @SerializedName("deliveryId")
        val deliveryId: Int,
        @SerializedName("estimatedTime")
        val estimatedTime: String,
        @SerializedName("progress")
        val progress: String,
        @SerializedName("title")
        val title: String,
    )
}
