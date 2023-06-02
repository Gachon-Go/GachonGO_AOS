package com.example.gachongo.presentation.main.home.go

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.data.response.ResponseDeliveryDto
import com.example.gachongo.presentation.main.home.detail.GoDetailActivity
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ItemDeliveryBinding

class GoDeliveryAdapter(var result: MutableList<ResponseDeliveryDto.Result>) :
    RecyclerView.Adapter<GoDeliveryAdapter.GoDeliveryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoDeliveryViewHolder {
        val binding =
            ItemDeliveryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoDeliveryViewHolder(binding)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: GoDeliveryViewHolder, position: Int) {
        holder.bind(result[position])
    }

    fun replaceList(newList: MutableList<ResponseDeliveryDto.Result>) {
        result = newList
        // 어댑터의 데이터가 변했다는 notify를 날린다
        notifyDataSetChanged()
    }

    class GoDeliveryViewHolder(private val binding: ItemDeliveryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseDeliveryDto.Result) {
            binding.tvDeliveryTitle.text = item.title
            binding.tvDeliveryTime.text = item.estimatedTime
            binding.tvDeliveryComment.text = item.commentNum.toString()
            if (item.progress == "모집완료"){
                binding.tvDeliveryStatus.text = item.progress
                binding.tvDeliveryStatus.setBackgroundResource(R.drawable.shape_pink400_fill_20_rect)
            }
            val deliveryPostId = item.deliveryId
            Log.d("GachonLog #게시글", "현재 뷰에 업데이트 중인 게시글 번호 $deliveryPostId")

            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, GoDetailActivity::class.java)
                intent.putExtra("deliveryPostId", deliveryPostId)
                ContextCompat.startActivity(binding.root.context, intent, null)
            }
        }
    }
}
