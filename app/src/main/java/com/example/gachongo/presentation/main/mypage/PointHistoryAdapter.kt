package com.example.gachongo.presentation.main.mypage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.data.PointHistoryResponseResult
import com.example.gachongo_aos.databinding.ItemPointHistoryBinding
import java.text.DecimalFormat

class PointHistoryAdapter(private val pointHistoryList: List<PointHistoryResponseResult>): RecyclerView.Adapter<PointHistoryAdapter.PointHistoryViewHolder>() {
    private val list = ArrayList<PointHistoryResponseResult>()

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

    @SuppressLint("NotifyDataSetChanged")
    fun addPointHistory(alarm: List<PointHistoryResponseResult>){
        this.list.clear()
        this.list.addAll(alarm)
        notifyDataSetChanged()
    }

    inner class PointHistoryViewHolder(val binding: ItemPointHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pointHistory: PointHistoryResponseResult) {
            binding.itemPointHistoryDate.text = pointHistory.time
            binding.itemPointHistoryContent.text = pointHistory.content
            binding.itemPointHistoryPoint.text = pointHistory.point

            // 서버에서 'P' 넘기는 여부에 따라 포인트 보여주는 방식 바꾸기 (ex) +500P / +500
            if(pointHistory.point.contains('P')) { binding.itemPointHistoryPointUnit.visibility = View.GONE }
            else { binding.itemPointHistoryPointUnit.visibility = View.VISIBLE }
        }
    }
}