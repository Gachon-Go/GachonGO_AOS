package com.example.gachongo.presentation.main.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo_aos.databinding.ItemActivityHistoryBinding

class ActHistoryAdapter(private val buildingNameList: ArrayList<String>): RecyclerView.Adapter<ActHistoryAdapter.ActHistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActHistoryAdapter.ActHistoryViewHolder {
        val binding: ItemActivityHistoryBinding = ItemActivityHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActHistoryAdapter.ActHistoryViewHolder, position: Int) {
        holder.bind(buildingNameList[position])
    }

    override fun getItemCount(): Int {
        return buildingNameList.size
    }

    inner class ActHistoryViewHolder(val binding: ItemActivityHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(buildingName: String) {
            binding.itemActivityHistoryNameTv.text = buildingName
        }
    }
}