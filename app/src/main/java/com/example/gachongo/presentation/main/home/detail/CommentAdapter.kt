package com.example.gachongo.presentation.main.home.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.data.ResponseCommentDto
import com.example.gachongo_aos.databinding.ItemDeliveryCommentBinding

class CommentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //    var data = arrayListOf<ResponseCommentDto.Result>()
//
//    class CommentViewHolder(private val binding: ItemDeliveryCommentBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun onBind(item: ResponseCommentDto.Result) {
//            binding.tvCommentName.text = item.commentWriter
//            binding.tvCommentDetail.text = item.content
////            binding.root.setOnLongClickListener {
////                // TO DO : 길게 클릭시 다이얼로그
////            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val binding =
//            ItemDeliveryCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return CommentViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
//        holder.onBind(data[position])
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}
