package com.example.gachongo.presentation.main.alarm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gachongo.api.NotificationService
import com.example.gachongo.api.NotificationView
import com.example.gachongo.data.NotificationBundle
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.databinding.FragmentAlarmBinding

class AlarmFragment : Fragment(), NotificationView {
    private lateinit var binding: FragmentAlarmBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(inflater, container, false)
        getNotification()
        return binding.root
    }

    private fun getNotification() {
        val notificationService = NotificationService(this)
        notificationService.getNotification(getUserJwt(requireContext()))
    }

    override fun onGetNotificationSuccess(notificationBundle: NotificationBundle) {
        Log.d("Notification", notificationBundle.toString())
    }

    override fun onGetNotificationFailure(message: String) {
        Log.d("Notification", "알림가져오기 오류: $message")
    }

}