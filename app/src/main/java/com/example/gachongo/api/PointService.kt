package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.PointHistoryResponse
import com.example.gachongo.data.PointResponse
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PointService(val pointView: PointView, val pointHistoryView: PointHistoryView) {

    private var pointService: PointInterface = NetworkModule.retrofit.create(PointInterface::class.java)

    fun getPoint(jwt: String){
        pointService.getPoint(jwt).enqueue(object : Callback<PointResponse> {
            override fun onResponse(call: Call<PointResponse>, response: Response<PointResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when(resp.code){
                        1000-> pointView.onGetPointResultSuccess(resp.result)
                        else-> pointView.onGetPointResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<PointResponse>, t: Throwable) {
                Log.d("포인트 조회 실패", t.message ?: "통신 오류")
            }
        })
    }

    fun getPointHistory(jwt: String, page: Int, size: Int){
        pointService.getPointHistory(jwt, page, size).enqueue(object : Callback<PointHistoryResponse> {
            override fun onResponse(call: Call<PointHistoryResponse>, response: Response<PointHistoryResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when(resp.code){
                        1000-> pointHistoryView.onGetPointHistoryResultSuccess(resp.result)
                        else-> pointHistoryView.onGetPointHistoryResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<PointHistoryResponse>, t: Throwable) {
                Log.d("포인트 내역 조회 실패", t.message ?: "통신 오류")
            }
        })
    }
}