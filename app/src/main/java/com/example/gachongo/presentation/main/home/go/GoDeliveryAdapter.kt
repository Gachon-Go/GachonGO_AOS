package com.example.gachongo.presentation.main.home.go

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.data.GoDeliveryInfo
import com.example.gachongo_aos.databinding.ItemDeliveryBinding

class GoDeliveryAdapter : RecyclerView.Adapter<GoDeliveryAdapter.GoDeliveryViewHolder>() {

    private var data = mutableListOf<GoDeliveryInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoDeliveryViewHolder {
        val binding =
            ItemDeliveryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoDeliveryViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: GoDeliveryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun replaceList(newList: MutableList<GoDeliveryInfo>) {
        data = newList.toMutableList()
        // 어댑터의 데이터가 변했다는 notify를 날린다
        notifyDataSetChanged()
    }

    class GoDeliveryViewHolder(private val binding: ItemDeliveryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GoDeliveryInfo) {
            binding.tvDeliveryTitle.text = item.title
            binding.tvDeliveryTime.text = item.time
            binding.tvDeliveryComment.text = item.comment.toString()
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, GoDetailActivity::class.java)
//                            intent.putExtra("contentId", item.key) 이 부분 처리 논의
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
    }
}
