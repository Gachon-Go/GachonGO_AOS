package com.example.gachongo.data

import com.google.gson.annotations.SerializedName

data class NotificationBundle(
    @SerializedName("todayNotificationList") val todayNotificationList: ArrayList<NotificationContent>,
    @SerializedName("yesterdayNotificationList") val yesterdayNotificationList: ArrayList<NotificationContent>,
    @SerializedName("pastNotificationList") val pastNotificationList: ArrayList<NotificationContent>,
)
