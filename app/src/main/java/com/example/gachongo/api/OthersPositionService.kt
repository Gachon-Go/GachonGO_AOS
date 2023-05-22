package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.OthersPositionResponse
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OthersPositionService(val othersPositionView: OthersPositionView) {
    fun getOthersPosition(jwt: String) {
        val othersPositionService = NetworkModule.retrofit.create(OthersPositionInterface::class.java)

        othersPositionService.getOthersPosition("Bearer $jwt").enqueue(object : Callback<OthersPositionResponse> {
            override fun onResponse(call: Call<OthersPositionResponse>, response: Response<OthersPositionResponse>) {
                val resp = response.body()
                Log.d("resp", resp.toString())
                if (resp != null) {
                    when (resp.code) {
                        1000 -> othersPositionView.onGetOthersPositionResultSuccess(resp.result)
                        else -> othersPositionView.onGetOthersPositionResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<OthersPositionResponse>, t: Throwable) {
                Log.d("사용자 위치정보 받아오기를 실패했어요", t.message ?: "통신 오류")
            }
        })
    }
}