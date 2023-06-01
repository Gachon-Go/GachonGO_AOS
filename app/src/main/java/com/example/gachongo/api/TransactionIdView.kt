package com.example.gachongo.api

import com.example.gachongo.data.TransactionIdResult

interface TransactionIdView {
    fun onGetTransactionIdResultSuccess(result: TransactionIdResult)
    fun onGetTransactionIdResultFailure(message: String)
}