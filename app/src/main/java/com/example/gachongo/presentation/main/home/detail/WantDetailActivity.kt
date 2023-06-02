package com.example.gachongo.presentation.main.home.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.example.gachongo.api.detail.WantDetailService
import com.example.gachongo.api.detail.WantDetailView
import com.example.gachongo.data.request.RequestCommentDto
import com.example.gachongo.data.response.ResponseOrderCommentDto
import com.example.gachongo.data.response.ResponseOrderDetailDto
import com.example.gachongo.util.binding.BindingActivity
import com.example.gachongo.util.extension.showToast
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityWantDetailBinding

class WantDetailActivity :
    BindingActivity<ActivityWantDetailBinding>(R.layout.activity_want_detail), WantDetailView {
    private val orderPostId: Int by lazy { intent.getIntExtra("orderPostId", 0) }
    private val wantDetailService = WantDetailService(this)
    private var isMine: Boolean = false

    private var commentAdapter: WantCommentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getDetail()
        getComment()
        initCommentBtnClickListener()
    }

    private fun getDetail() {
        val jwt: String = getUserJwt(this)
        wantDetailService.getDetail(jwt, orderPostId)
    }

    private fun getComment() {
        val jwt: String = getUserJwt(this)
        wantDetailService.getOrderDetailComment(jwt, orderPostId, 0, 10)
    }

    private fun postComment(comment: String) {
        val jwt: String = getUserJwt(this)
        wantDetailService.postOrderDetailComment(
            jwt,
            orderPostId,
            body = RequestCommentDto(comment),
        )
    }

    private fun initCommentBtnClickListener() {
        binding.btnCommentDone.setOnClickListener() {
            val comment = binding.etCommentInput.text.toString()
            postComment(comment)
        }
    }

    private fun initCommentAdapter(
        result: MutableList<ResponseOrderCommentDto.Result>,
        isMine: Boolean,
        orderPostId: Int,
    ) {
        commentAdapter = WantCommentAdapter(result, isMine, orderPostId)
        binding.rvDetailComment.adapter = commentAdapter
        binding.rvDetailComment.layoutManager = LinearLayoutManager(this)
    }

    private fun initOrderDoneBtnClickListener() {
        if (isMine) {
            binding.btnOrderDone.visibility = View.VISIBLE
            binding.btnOrderDone.setOnClickListener() {
                val jwt: String = getUserJwt(this)
                wantDetailService.postOrderDone(jwt, orderPostId)
            }
        }
    }
    override fun onGetOrderDetailResultSuccess(result: ResponseOrderDetailDto) {
        binding.tvWantDetailTitle.text = result.result.title
        binding.tvWantDetailContent.text = result.result.content
        binding.tvDeliveryTime.text = result.result.estimatedTime
        binding.tvWantDetailName.text = result.result.writer
        isMine = result.result.mine
        binding.ivDetailProfile.load(result.result.writerImage){
            placeholder(R.drawable.bg_button_default)
            transformations(CircleCropTransformation())
        }
        initOrderDoneBtnClickListener()
    }

    private fun activityRestart() {
        val intent = intent
        finish()
        startActivity(intent)
    }
    override fun onGetOrderDetailResultFailure(message: String) {
        Log.d("GachonLog #게시물", "게시물 상세내용 가져오기 실패 $message")
    }

    override fun onGetOrderCommentResultSuccess(result: MutableList<ResponseOrderCommentDto.Result>) {
        Log.d("GachonLog #댓글", "배달해주세요: 댓글 목록 가져오기 성공, recycler view 적용 시작")
        initCommentAdapter(result, isMine, orderPostId)
    }

    override fun onGetOrderCommentResultFailure(message: String) {
        Log.d("GachonLog #댓글", "배달해주세요: 댓글 목록 가져오기 실패 $message")
    }

    override fun onPostOrderCommentResultSuccess() {
        Log.d("GachonLog #댓글", "배달해주세요: 댓글 작성 완료")
        activityRestart()
    }

    override fun onPostOrderCommentResultFailure(message: String) {
        Log.d("GachonLog #댓글", "배달해주세요: 댓글 작성 실패")
    }

    override fun onPostOrderDoneResultSuccess() {
        showToast("배달이 시작되었습니다.")
        finish()
    }

    override fun onPostOrderDoneResultFailure(message: String) {
        TODO("Not yet implemented")
    }
}
