package com.example.gachongo.api

import com.example.gachongo.data.OthersPositionResult

interface OthersPositionView {
    fun onGetOthersPositionResultSuccess(result: OthersPositionResult)
    fun onGetOthersPositionResultFailure(message: String)
}