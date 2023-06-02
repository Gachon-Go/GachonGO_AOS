package com.example.gachongo.api.select

interface OrderSelectView {
    fun onGetOrderSelectResultSuccess()
    fun onGetOrderSelectResultFailure(message: String)
}