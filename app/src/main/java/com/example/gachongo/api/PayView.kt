package com.example.gachongo.api

import com.example.gachongo.data.TransactionIdResult

interface PayView {
    fun onSendPayResultSuccess()
    fun onSendPayResultFailure(message: String)
}