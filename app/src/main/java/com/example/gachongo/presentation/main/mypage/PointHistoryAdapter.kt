package com.example.gachongo.presentation.main.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.data.PointHistoryResponseResult
import com.example.gachongo_aos.databinding.ItemPointHistoryBinding

class PointHistoryAdapter(private val pointHistoryList: ArrayList<PointHistoryResponseResult>): RecyclerView.Adapter<PointHistoryAdapter.PointHistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointHistoryAdapter.PointHistoryViewHolder {
        val binding: ItemPointHistoryBinding = ItemPointHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PointHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PointHistoryAdapter.PointHistoryViewHolder, position: Int) {
        holder.bind(pointHistoryList[position])
    }

    override fun getItemCount(): Int {
        return pointHistoryList.size
    }

    inner class PointHistoryViewHolder(val binding: ItemPointHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pointHistory: PointHistoryResponseResult) {
            binding.itemPointHistoryDate.text = pointHistory.time
            binding.itemPointHistoryContent.text = pointHistory.content
            binding.itemPointHistoryPoint.text = pointHistory.point
        }
    }
}