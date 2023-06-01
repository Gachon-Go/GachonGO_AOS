package com.example.gachongo.presentation.main.home.want

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.data.response.ResponseOrderDto
import com.example.gachongo.presentation.main.home.detail.WantDetailActivity
import com.example.gachongo_aos.databinding.ItemDeliveryBinding

class WantDeliveryAdapter(var result: MutableList<ResponseOrderDto.Result>) :
    RecyclerView.Adapter<WantDeliveryAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val binding: ItemDeliveryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseOrderDto.Result) {
            binding.tvDeliveryTitle.text = item.title
            binding.tvDeliveryTime.text = item.estimatedTime
            binding.tvDeliveryComment.text = item.commentNum.toString()
            val orderId = item.orderId

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, WantDetailActivity::class.java)
                intent.putExtra("orderPostId", orderId)
                startActivity(binding.root.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemDeliveryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(result[position])
    }
}
