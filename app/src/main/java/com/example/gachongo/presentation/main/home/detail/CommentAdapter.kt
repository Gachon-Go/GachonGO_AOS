package com.example.gachongo.presentation.main.home.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.data.response.ResponseDeliveryCommentDto
import com.example.gachongo_aos.databinding.ItemDeliveryCommentBinding

class CommentAdapter(var result: MutableList<ResponseDeliveryCommentDto.Result>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(private val binding: ItemDeliveryCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseDeliveryCommentDto.Result) {
            binding.tvCommentName.text = item.commentWriter
            binding.tvCommentDetail.text = item.content
//            binding.root.setOnLongClickListener {}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding =
            ItemDeliveryCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(result[position])
    }
}
