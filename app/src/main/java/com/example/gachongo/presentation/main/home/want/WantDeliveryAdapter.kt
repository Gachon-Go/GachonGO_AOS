package com.example.gachongo.presentation.main.home.want

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.data.GoDeliveryInfo
import com.example.gachongo_aos.databinding.ItemDeliveryBinding

class WantDeliveryAdapter : RecyclerView.Adapter<WantDeliveryAdapter.ItemViewHolder>() {

    var data = listOf<GoDeliveryInfo>()

    class ItemViewHolder(private val binding: ItemDeliveryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: GoDeliveryInfo) {
            // TO DO : 들어갈 내용 추가하기
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemDeliveryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}
