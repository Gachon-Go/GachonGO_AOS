package com.example.gachongo.api

interface NicknameView {
    fun onGetNicknameResultSuccess()
    fun onGetNicknameResultFailure(message: String)
}