package com.example.gachongo.api

interface SignUpView {
    fun onGetSignUpResultSuccess()
    fun onGetSignUpResultFailure(message: String)
}