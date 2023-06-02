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
import com.example.gachongo.data.NotificationContent
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.databinding.FragmentAlarmBinding

class AlarmFragment : Fragment(), NotificationView {
    private lateinit var binding: FragmentAlarmBinding

    private lateinit var todayAlarmRVAdapter: AlarmRVAdapter
    private lateinit var yesterdayAlarmRVAdapter: AlarmRVAdapter
    private lateinit var pastAlarmRVAdapter: AlarmRVAdapter

    private val todayAlarmList = ArrayList<NotificationContent>()
    private val yesterdayAlarmList = ArrayList<NotificationContent>()
    private val pastAlarmList = ArrayList<NotificationContent>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(inflater, container, false)
        getNotification()

        todayAlarmRVAdapter = AlarmRVAdapter(requireContext(), todayAlarmList)
        yesterdayAlarmRVAdapter = AlarmRVAdapter(requireContext(), yesterdayAlarmList)
        pastAlarmRVAdapter = AlarmRVAdapter(requireContext(), pastAlarmList)

        binding.alarmTodayRv.adapter = todayAlarmRVAdapter
        binding.alarmYesterdayRv.adapter = yesterdayAlarmRVAdapter
        binding.alarmPastRv.adapter = pastAlarmRVAdapter

        return binding.root
    }

    private fun getNotification() {
        val notificationService = NotificationService(this)
        notificationService.getNotification(getUserJwt(requireContext()))
    }

    override fun onGetNotificationSuccess(notificationBundle: NotificationBundle) {
        Log.d("GachonLog #알림", "나의 알림 목록 가져오기 성공")
        binding.alarmTodayTv.visibility = if (notificationBundle.todayNotificationList.isNullOrEmpty()) View.GONE else View.VISIBLE
        binding.alarmTodayRv.visibility = if (notificationBundle.todayNotificationList.isNullOrEmpty()) View.GONE else View.VISIBLE
        binding.alarmYesterdayTv.visibility = if (notificationBundle.yesterdayNotificationList.isNullOrEmpty()) View.GONE else View.VISIBLE
        binding.alarmYesterdayRv.visibility = if (notificationBundle.yesterdayNotificationList.isNullOrEmpty()) View.GONE else View.VISIBLE
        binding.alarmPastTv.visibility = if (notificationBundle.pastNotificationList.isNullOrEmpty()) View.GONE else View.VISIBLE
        binding.alarmPastRv.visibility = if (notificationBundle.pastNotificationList.isNullOrEmpty()) View.GONE else View.VISIBLE


        todayAlarmRVAdapter.addNotification(notificationBundle.todayNotificationList)
        yesterdayAlarmRVAdapter.addNotification(notificationBundle.yesterdayNotificationList)
        pastAlarmRVAdapter.addNotification(notificationBundle.pastNotificationList)
    }

    override fun onGetNotificationFailure(message: String) {
        Log.d("GachonLog #알림", "알림가져오기 오류: $message")
    }

}