package com.example.gachongo.presentation.main.home.detail

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gachongo.api.detail.GoDetailService
import com.example.gachongo.api.detail.GoDetailView
import com.example.gachongo.data.request.RequestCommentDto
import com.example.gachongo.data.response.ResponseDeliveryCommentDto
import com.example.gachongo.data.response.ResponseDeliveryDetailDto
import com.example.gachongo.util.binding.BindingActivity
import com.example.gachongo.util.extension.showToast
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityGoDetailBinding

class GoDetailActivity :
    BindingActivity<ActivityGoDetailBinding>(R.layout.activity_go_detail), GoDetailView {

    private val deliveryPostId: Int by lazy { intent.getIntExtra("deliveryPostId", 0) }
    private val goDetailService = GoDetailService(this)
    private var isMine: Boolean = false

    private var commentAdapter: CommentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("after", deliveryPostId.toString())
        getDetail()
        getComment()
        initCommentBtnClickListener()
        initDeliveryDoneBtnClickListener()
    }

    private fun initCommentAdapter(
        result: MutableList<ResponseDeliveryCommentDto.Result>,
        isMine: Boolean,
        deliveryPostId: Int,
    ) {
        commentAdapter = CommentAdapter(result, isMine, deliveryPostId)
        binding.rvDetailComment.adapter = commentAdapter
        binding.rvDetailComment.layoutManager = LinearLayoutManager(this)
    }

    private fun getDetail() {
        val jwt: String = getUserJwt(this)
        goDetailService.getDetail(jwt, deliveryPostId)
    }

    private fun getComment() {
        val jwt: String = getUserJwt(this)
        goDetailService.getDeliveryDetailComment(jwt, deliveryPostId, 0, 10)
    }

    private fun onPostComment(content: String) {
        val jwt: String = getUserJwt(this)
        goDetailService.postDeliveryDetailComment(
            jwt,
            deliveryPostId,
            body = RequestCommentDto(content),
        )
        // TODO: post 후 새로고침
    }

    private fun initCommentBtnClickListener() {
        binding.btnCommentDone.setOnClickListener() {
            val comment = binding.etCommentInput.text.toString()
            onPostComment(comment)
        }
    }
    private fun initDeliveryDoneBtnClickListener(){
        binding.btnDeliveryDone.setOnClickListener(){
            val jwt: String = getUserJwt(this)
            goDetailService.postDeliveryDone(jwt, deliveryPostId)
        }
    }
    override fun onGetDeliveryDetailResultSuccess(result: ResponseDeliveryDetailDto) {
        binding.tvGoDetailTitle.text = result.result.title
        binding.tvGoDetailContent.text = result.result.content
        binding.tvDeliveryTime.text = result.result.estimatedTime
        binding.tvGoDetailName.text = result.result.writer
        isMine = result.result.mine
    }

    override fun onGetDeliveryDetailResultFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetDeliveryCommentResultSuccess(result: MutableList<ResponseDeliveryCommentDto.Result>) {
        initCommentAdapter(result, isMine, deliveryPostId)
    }

    override fun onGetDeliveryCommentResultFailure(message: String) {
        Log.d("error", "댓글 정보 조회 실패 $message")
    }

    override fun onPostDeliveryCommentResultSuccess() {
        TODO("Not yet implemented")
    }

    override fun onPostDeliveryCommentResultFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPostDeliveryDoneResultSuccess() {
        showToast("배달을 시작해주세요")
        finish()
    }

    override fun onPostDeliveryDoneResultFailure(message: String) {
        TODO("Not yet implemented")
    }
}
