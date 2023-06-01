package com.example.gachongo.api

import com.example.gachongo.data.PointHistoryResponseResult
import com.example.gachongo.data.PointResponseResult

interface PointView {
    fun onGetPointResultSuccess(result: PointResponseResult)
    fun onGetPointResultFailure(message: String)
}

interface PointHistoryView {
    fun onGetPointHistoryResultSuccess(result: ArrayList<PointHistoryResponseResult>)
    fun onGetPointHistoryResultFailure(message: String)
}