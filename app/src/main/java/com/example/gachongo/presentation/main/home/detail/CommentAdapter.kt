package com.example.gachongo.presentation.main.home.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.api.select.DeliverySelectService
import com.example.gachongo.api.select.DeliverySelectView
import com.example.gachongo.data.response.ResponseDeliveryCommentDto
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ItemDeliveryCommentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class CommentAdapter(
    var result: MutableList<ResponseDeliveryCommentDto.Result>,
    val isMine: Boolean,
    val deliveryPostId: Int,
) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    class CommentViewHolder(private val binding: ItemDeliveryCommentBinding, val isMine: Boolean, val deliveryPostId: Int) :
        RecyclerView.ViewHolder(binding.root), DeliverySelectView {
        fun bind(item: ResponseDeliveryCommentDto.Result) {
            binding.tvCommentName.text = item.commentWriter
            binding.tvCommentDetail.text = item.content
            binding.ivCommentAccepted.visibility = if (item.isAccept) View.VISIBLE else View.INVISIBLE

            if (isMine and !item.isAccept) {
                binding.root.setOnLongClickListener {
                    val bottomSheetDialog =
                        BottomSheetDialog(binding.root.context, R.style.BottomSheetDialogTheme)
                    val bottomSheetView = LayoutInflater.from(binding.root.context)
                        .inflate(R.layout.layout_bottom_sheet, null)
                    val btnOk: Button = bottomSheetView.findViewById(R.id.btn_ok)
                    val deliverySelectService: DeliverySelectService = DeliverySelectService(this)
                    btnOk.setOnClickListener() {
                        // 배달 수락
                        val jwt: String = getUserJwt(binding.root.context)
                        val commentId = item.commentId
                        deliverySelectService.postDeliveryDetailCommentSelect(jwt, deliveryPostId, commentId)
                    }
                    bottomSheetDialog.setContentView(bottomSheetView)
                    bottomSheetDialog.show()
                    true
                }
            }
        }

        override fun onGetDeliverySelectResultSuccess() {
            Toast.makeText(binding.root.context, "거래가 시작되었습니다", Toast.LENGTH_LONG).show()
        }

        override fun onDeliverySelectResultFailure(message: String) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding =
            ItemDeliveryCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding, isMine, deliveryPostId)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(result[position])
    }
}
