package com.example.gachongo.presentation.main.alarm

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gachongo.data.NotificationContent
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ItemAlarmBinding

class AlarmRVAdapter(var context: Context, private var notificationContentList: ArrayList<NotificationContent>): RecyclerView.Adapter<AlarmRVAdapter.AlarmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val binding:ItemAlarmBinding = ItemAlarmBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AlarmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmRVAdapter.AlarmViewHolder, position: Int) {
        holder.bind(notificationContentList[position])
    }

    override fun getItemCount(): Int {
        return notificationContentList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addNotification(notificationContent: ArrayList<NotificationContent>?){
        this.notificationContentList.clear()
        if (notificationContent != null) {
            this.notificationContentList.addAll(notificationContent)
        }

        notifyDataSetChanged()
    }

    inner class AlarmViewHolder(val binding: ItemAlarmBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(notificationContent: NotificationContent) {
            when(notificationContent.flag) {
                1 -> binding.itemAlarmIcon.setImageResource(R.drawable.ic_notification_check)
                2 -> binding.itemAlarmIcon.setImageResource(R.drawable.ic_notification_point)
                3 -> binding.itemAlarmIcon.setImageResource(R.drawable.ic_notification_food)
                4 -> binding.itemAlarmIcon.setImageResource(R.drawable.ic_notification_message)
            }

            binding.itemAlarmContent.text = notificationContent.content
        }
    }
}
