package com.example.gachongo.presentation.main.mypage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo.api.PointHistoryView
import com.example.gachongo.api.PointService
import com.example.gachongo.api.PointView
import com.example.gachongo.data.PointHistoryResponseResult
import com.example.gachongo.data.PointResponseResult
import com.example.gachongo.util.extension.backPressed
import com.example.gachongo_aos.databinding.ActivityPointManageBinding
import com.example.gachongo.util.getUserJwt

class PointManageActivity : AppCompatActivity(), PointView, PointHistoryView {
    private lateinit var binding: ActivityPointManageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPointManageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPoint()

        binding.pointManageBackIv.setOnClickListener {
            backPressed()
        }
    }

    private fun getPoint() {
        val pointService = PointService(this, this)
        pointService.getPoint(getUserJwt(this))
        // TODO 리사이클러뷰가 바닥에 닿으면 page++ 해주기
        pointService.getPointHistory(getUserJwt(this), 0, 10)
    }

    override fun onGetPointResultSuccess(result: PointResponseResult) {
        binding.pointManagePointTv.text = result.point.toString()
    }

    override fun onGetPointResultFailure(message: String) {
        Log.d("포인트 조회 오류", message)
    }

    override fun onGetPointHistoryResultSuccess(result: ArrayList<PointHistoryResponseResult>) {
        binding.pointManageHistoryRv.adapter = PointHistoryAdapter(result)
    }

    override fun onGetPointHistoryResultFailure(message: String) {
        Log.d("포인트 내역 조회 오류", message)
    }
}