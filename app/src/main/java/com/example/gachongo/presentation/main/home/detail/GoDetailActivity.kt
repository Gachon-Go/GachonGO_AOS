package com.example.gachongo.presentation.main.home.detail

import android.os.Bundle
import android.util.Log
import com.example.gachongo.api.detail.GoDetailService
import com.example.gachongo.api.detail.GoDetailView
import com.example.gachongo.data.response.ResponseDeliveryCommentDto
import com.example.gachongo.data.response.ResponseDeliveryDetailDto
import com.example.gachongo.util.binding.BindingActivity
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityGoDetailBinding

class GoDetailActivity :
    BindingActivity<ActivityGoDetailBinding>(R.layout.activity_go_detail), GoDetailView {

    private val deliveryPostId: Int by lazy { intent.getIntExtra("deliveryPostId", 0) }
    private var commentAdapter: CommentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initCommentAdapter(result: MutableList<ResponseDeliveryCommentDto.Result>) {
        commentAdapter = CommentAdapter(result)
        binding.rvDetailComment.adapter = commentAdapter
    }

    private fun getDetail() {
        val jwt: String = getUserJwt(this)

        val goDetailService = GoDetailService(this)
        goDetailService.getDetail(jwt, deliveryPostId)
    }
    override fun onGetDeliveryDetailResultSuccess(result: ResponseDeliveryDetailDto) {
        getDetail()

        binding.tvGoDetailTitle.text = result.result.title
        binding.tvGoDetailContent.text = result.result.content
        binding.tvDeliveryTime.text = result.result.estimatedTime
        binding.tvGoDetailName.text = result.result.writer
    }

    override fun onGetDeliveryDetailResultFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetDeliveryCommentResultSuccess(result: MutableList<ResponseDeliveryCommentDto.Result>) {
        initCommentAdapter(result)
    }

    override fun onGetDeliveryCommentResultFailure(message: String) {
        Log.d("error", "댓글 정보 조회 실패 $message")
    }
}
