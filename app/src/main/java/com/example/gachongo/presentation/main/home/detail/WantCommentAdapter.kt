package com.example.gachongo.presentation.main.home.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.api.select.OrderSelectService
import com.example.gachongo.api.select.OrderSelectView
import com.example.gachongo.data.response.ResponseOrderCommentDto
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ItemDeliveryCommentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class WantCommentAdapter(
    val result: MutableList<ResponseOrderCommentDto.Result>,
    val isMine: Boolean,
    val orderPostId: Int,
) : RecyclerView.Adapter<WantCommentAdapter.CommentViewHolder>() {
    class CommentViewHolder(
        private val binding: ItemDeliveryCommentBinding,
        val isMine: Boolean,
        val orderPostId: Int,
    ) :
        RecyclerView.ViewHolder(binding.root), OrderSelectView {
        fun bind(item: ResponseOrderCommentDto.Result) {
            binding.tvCommentName.text = item.commentWriter
            binding.tvCommentDetail.text = item.content
            binding.ivCommentAccepted.visibility =
                if (item.isAccept) View.VISIBLE else View.INVISIBLE

            if (isMine and !item.isAccept) {
                binding.root.setOnLongClickListener {
                    val bottomSheetDialog =
                        BottomSheetDialog(binding.root.context, R.style.BottomSheetDialogTheme)
                    val bottomSheetView = LayoutInflater.from(binding.root.context)
                        .inflate(R.layout.layout_bottom_sheet, null)
                    val btnOk: Button = bottomSheetView.findViewById(R.id.btn_ok)
                    val orderSelectService: OrderSelectService = OrderSelectService(this)
                    btnOk.setOnClickListener() {
                        // 배달 수락
                        val jwt: String = getUserJwt(binding.root.context)
                        val commentId = item.commentId
                        orderSelectService.postOrderCommentSelect(jwt, orderPostId, commentId)
                    }
                    bottomSheetDialog.setContentView(bottomSheetView)
                    bottomSheetDialog.show()
                    true
                }
            }
        }

        override fun onGetOrderSelectResultSuccess() {
            TODO("Not yet implemented")
        }

        override fun onGetOrderSelectResultFailure(message: String) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding =
            ItemDeliveryCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding, isMine, orderPostId)
    }

    override fun getItemCount(): Int {
        return result.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(result[position])
    }
}
