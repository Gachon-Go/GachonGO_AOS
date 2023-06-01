package com.example.gachongo.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.api.PointHistoryView
import com.example.gachongo.api.PointService
import com.example.gachongo.api.PointView
import com.example.gachongo.data.PointHistoryResponseResult
import com.example.gachongo.data.PointResponseResult
import com.example.gachongo.util.extension.backPressed
import com.example.gachongo.util.getUserJwt
import com.example.gachongo.util.saveUserPoint
import com.example.gachongo_aos.databinding.ActivityPointManageBinding
import java.text.DecimalFormat


class PointManageActivity : AppCompatActivity(), PointView, PointHistoryView {
    private lateinit var binding: ActivityPointManageBinding
    private lateinit var pointHistoryAdapter: PointHistoryAdapter

    private var page = 0
    private var jwt: String = ""
    private val list = mutableListOf<PointHistoryResponseResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPointManageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        jwt = getUserJwt(this)
        getPoint()

        binding.pointManageChargingBtn.setOnClickListener {
            startActivity(Intent(this, PointChargeActivity::class.java))
            finish()
        }

        binding.pointManageBackIv.setOnClickListener {
            backPressed()
        }
    }

    private fun getPoint() {
        val pointService = PointService(this, this)
        pointService.getPoint(jwt)
        pointService.getPointHistory(jwt, 0, 10)

        // 리사이클러뷰 스크롤 하단에 도달하면 page 늘려주기
        binding.pointManageHistoryRv.apply {
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
                    val itemTotalCount = recyclerView.adapter!!.itemCount
                    if (lastVisibleItemPosition >= itemTotalCount - 1) {
                        page++
                        pointService.getPointHistory(jwt, page, 10)
                    }
                }
            })
        }

        pointHistoryAdapter = PointHistoryAdapter(list)
        binding.pointManageHistoryRv.adapter = pointHistoryAdapter
    }

    override fun onGetPointResultSuccess(result: PointResponseResult) {
        binding.pointManagePointTv.text = DecimalFormat("#,###").format(result.point).toString()
        saveUserPoint(this, result.point)
    }

    override fun onGetPointResultFailure(message: String) {
        Log.d("포인트 조회 오류", message)
    }

    override fun onGetPointHistoryResultSuccess(result: ArrayList<PointHistoryResponseResult>) {
        result.forEach { list.add(it) }
        pointHistoryAdapter.addPointHistory(list)
    }

    override fun onGetPointHistoryResultFailure(message: String) {
        Log.d("포인트 내역 조회 오류", message)
    }
}