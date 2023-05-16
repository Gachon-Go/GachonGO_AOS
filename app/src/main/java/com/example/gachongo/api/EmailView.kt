package com.example.gachongo.api

interface EmailView {
    fun onGetEmailResultSuccess()
    fun onGetEmailResultFailure(message: String)
}

interface EmailCodeView {
    fun onGetEmailCodeResultSuccess()
    fun onGetEmailCodeResultFailure(message: String)
}