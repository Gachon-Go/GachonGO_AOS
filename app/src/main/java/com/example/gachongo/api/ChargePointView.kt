package com.example.gachongo.api

import com.example.gachongo.data.ChargePointResult

interface ChargePointView {
    fun onSendChargePointResultSuccess(result: ChargePointResult)
    fun onSendChargePointResultFailure(message: String)
}