package com.example.gachongo.api

interface SendPositionView {
    fun onGetSendPositionResultSuccess()
    fun onGetSendPositionResultFailure(message: String)
}