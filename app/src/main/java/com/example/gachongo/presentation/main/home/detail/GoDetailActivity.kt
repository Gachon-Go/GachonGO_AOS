package com.example.gachongo.presentation.main.home.detail

import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gachongo.util.binding.BindingActivity
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityGoDetailBinding

class GoDetailActivity :
    BindingActivity<ActivityGoDetailBinding>(R.layout.activity_go_detail) {

    private val contentId: Int by lazy { intent.getIntExtra("deliveryId", 0) }
    private var commentAdapter : CommentAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getContent(contentId)
        initCommentAdapter()

    }

    private fun getContent(contentId: Int) {
        // TO DO : 글 상세내용 불러오기
    }
    private fun initCommentAdapter(){
        commentAdapter = CommentAdapter()
        binding.rvDetailComment.adapter = commentAdapter
    }
}
