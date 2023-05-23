package com.example.gachongo.presentation.main.home.go

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.data.ResponseDeliveryDto
import com.example.gachongo.presentation.main.home.detail.GoDetailActivity
import com.example.gachongo_aos.databinding.ItemDeliveryBinding

class GoDeliveryAdapter : RecyclerView.Adapter<GoDeliveryAdapter.GoDeliveryViewHolder>() {

    private var data = mutableListOf<ResponseDeliveryDto.Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoDeliveryViewHolder {
        val binding =
            ItemDeliveryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoDeliveryViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: GoDeliveryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun replaceList(newList: MutableList<ResponseDeliveryDto.Result>) {
        data = newList.toMutableList()
        // 어댑터의 데이터가 변했다는 notify를 날린다
        notifyDataSetChanged()
    }

    class GoDeliveryViewHolder(private val binding: ItemDeliveryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseDeliveryDto.Result) {
            binding.tvDeliveryTitle.text = item.title
            binding.tvDeliveryTime.text = item.estimatedTime
            binding.tvDeliveryComment.text = item.commentNum.toString()

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, GoDetailActivity::class.java)
                intent.putExtra("deliveryId", item.deliveryId)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
    }
}
