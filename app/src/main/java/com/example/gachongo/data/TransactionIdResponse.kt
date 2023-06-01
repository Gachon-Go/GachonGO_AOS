package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class TransactionIdResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: TransactionIdResult,
)
