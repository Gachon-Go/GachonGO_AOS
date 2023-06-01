package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class TransactionIdResult(
    @SerializedName("transactionId") val transactionId: String,
)
