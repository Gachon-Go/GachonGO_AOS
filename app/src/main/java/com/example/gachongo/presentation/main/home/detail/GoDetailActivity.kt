package com.example.gachongo.presentation.main.home.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
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

        Log.d("GachonLog #게시글", "현재 게시글 #$deliveryPostId 에 대한 정보 불러오기")
        getDetail()
        getComment()
        initCommentBtnClickListener()
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

    private fun initDeliveryDoneBtnClickListener() {
        if (isMine) {
            binding.btnDeliveryDone.visibility = View.VISIBLE
            binding.btnDeliveryDone.setOnClickListener() {
                val jwt: String = getUserJwt(this)
                goDetailService.postDeliveryDone(jwt, deliveryPostId)
            }
        }
    }
    private fun activityRestart() {
        val intent = intent
        finish()
        startActivity(intent)
    }

    override fun onGetDeliveryDetailResultSuccess(result: ResponseDeliveryDetailDto) {
        Log.d("GachonLog #게시글", "배달갈게요: 게시글 상세정보 불러오기 성공")

        binding.tvGoDetailTitle.text = result.result.title
        binding.tvGoDetailContent.text = result.result.content
        binding.tvDeliveryTime.text = result.result.estimatedTime
        binding.tvGoDetailName.text = result.result.writer
        isMine = result.result.mine
        binding.ivDetailProfile.load(result.result.writerImage) {
            placeholder(R.drawable.bg_button_default)
            transformations(CircleCropTransformation())
        }

        initDeliveryDoneBtnClickListener()
    }

    override fun onGetDeliveryDetailResultFailure(message: String) {
        Log.d("GachonLog #게시글", "배달갈게요: 게시글 상세정보 불러오기 실패")
    }

    override fun onGetDeliveryCommentResultSuccess(result: MutableList<ResponseDeliveryCommentDto.Result>) {
        Log.d("GachonLog #댓글", "배달갈게요: 댓글 정보 조회 성공")
        initCommentAdapter(result, isMine, deliveryPostId)
    }

    override fun onGetDeliveryCommentResultFailure(message: String) {
        Log.d("GachonLog #댓글", "배달갈게요: 댓글 정보 조회 실패 $message")
    }

    override fun onPostDeliveryCommentResultSuccess() {
        Log.d("GachonLog #댓글", "배달갈게요: 댓글 작성 완료")
        activityRestart()
    }

    override fun onPostDeliveryCommentResultFailure(message: String) {
        Log.d("GachonLog #댓글", "댓글 작성 실패")
    }

    override fun onPostDeliveryDoneResultSuccess() {
        Log.d("GachonLog #게시글", "배달갈게요: 모집완료 성공")
        showToast("배달을 시작해주세요")
        finish()
    }

    override fun onPostDeliveryDoneResultFailure(message: String) {
        Log.d("GachonLog #게시글", "배달갈게요: 모집완료 실패")
    }
}
